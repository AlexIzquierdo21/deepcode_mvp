# Arquitectura MVVM con Clean Architecture

## ¿Qué es MVVM?

MVVM (Model-View-ViewModel) es un patrón de arquitectura que separa la lógica de negocio de la interfaz de usuario.

**Componentes principales:**
- **Model**: Los datos de la aplicación
- **View**: La interfaz de usuario (Composables)
- **ViewModel**: Intermediario entre View y Model, contiene la lógica de presentación

---

## Capas de Clean Architecture

### 1. **UI Layer** (`ui/`)
**Responsabilidad:** Mostrar datos al usuario y capturar interacciones.

**Contiene:**
- **Screens/Composables**: Funciones composables que renderizan la UI
- **ViewModels**: Gestiona el estado de la UI y la lógica de presentación
- **UI States**: Data classes que representan el estado de la pantalla
- **UI Events**: Eventos que el usuario genera (clicks, inputs, etc.)

**Ejemplo:**
```kotlin
// LoginScreen.kt - Vista
@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val state = viewModel.state.collectAsState()
    
    TextField(
        value = state.value.email,
        onValueChange = { viewModel.onEvent(LoginEvent.EmailChanged(it)) }
    )
}

// LoginViewModel.kt - ViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()
    
    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.EmailChanged -> {
                _state.value = _state.value.copy(email = event.email)
            }
            is LoginEvent.Login -> {
                viewModelScope.launch {
                    loginUseCase(email, password)
                }
            }
        }
    }
}
```

**Reglas:**
- La UI **NUNCA** accede directamente a repositorios o fuentes de datos
- Solo se comunica con el ViewModel
- No contiene lógica de negocio

---

### 2. **Domain Layer** (`domain/`)
**Responsabilidad:** Lógica de negocio pura, independiente de frameworks.

**Contiene:**

#### **a) Models/Entities** (`domain/model/`)
Representan las entidades del dominio de tu aplicación.
```kotlin
// Product.kt
data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val stock: Int
)
```

**Características:**
- Son las entidades del mundo real de tu app
- Independientes de la base de datos o API
- No tienen anotaciones de Room, Retrofit, etc.

#### **b) Use Cases** (`domain/usecase/`)
Cada caso de uso representa una acción específica del usuario.
```kotlin
// GetProductUseCase.kt
class GetProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(productId: String): Result<Product> {
        return repository.getProduct(productId)
    }
}
```

**Características:**
- Un caso de uso = Una acción del usuario
- Contiene lógica de negocio específica
- Coordina llamadas a repositorios
- Reutilizable en diferentes partes de la app

**Ejemplos de casos de uso:**
- `LoginUseCase`: Validar credenciales y autenticar usuario
- `GetProductsUseCase`: Obtener lista de productos
- `AddToCartUseCase`: Añadir producto al carrito con validaciones

#### **c) Repository Interfaces** (`domain/repository/`)
Define **QUÉ** operaciones se pueden hacer, no **CÓMO** se hacen.
```kotlin
// ProductRepository.kt (interface en domain)
interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>>
    suspend fun getProduct(id: String): Result<Product>
    suspend fun saveProduct(product: Product): Result<Unit>
}
```

**Reglas:**
- Solo interfaces, **no implementaciones**
- La implementación real está en la capa `data`
- Permite cambiar la fuente de datos sin afectar la lógica de negocio

---

### 3. **Data Layer** (`data/`)
**Responsabilidad:** Obtener y guardar datos desde diferentes fuentes.

**Contiene:**

#### **a) Repository Implementations** (`data/repository/`)
Implementa las interfaces definidas en `domain/repository/`.
```kotlin
// ProductRepositoryImpl.kt
class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource
) : ProductRepository {
    
    override suspend fun getProducts(): Result<List<Product>> {
        return try {
            // Intenta obtener de la API
            val products = remoteDataSource.getProducts()
            // Guarda en cache local
            localDataSource.saveProducts(products)
            Result.success(products)
        } catch (e: Exception) {
            // Si falla, devuelve cache local
            val cachedProducts = localDataSource.getProducts()
            if (cachedProducts.isNotEmpty()) {
                Result.success(cachedProducts)
            } else {
                Result.failure(e)
            }
        }
    }
}
```

**Características:**
- Decide de dónde obtener los datos (API, BD local, cache)
- Maneja errores y fallbacks
- Convierte DTOs (objetos de red/BD) a entidades del dominio

#### **b) Models/DTOs** (`data/model/`)
Representan los datos tal como vienen de la API o base de datos.
```kotlin
// ProductDto.kt (datos de la API)
data class ProductDto(
    @SerializedName("product_id") val id: String,
    @SerializedName("product_name") val name: String,
    @SerializedName("price") val price: Double,
    @SerializedName("stock_quantity") val stock: Int
)

// Mapper para convertir DTO -> Entity del dominio
fun ProductDto.toDomain() = Product(
    id = id,
    name = name,
    price = price,
    stock = stock
)
```

**Diferencia DTO vs Entity:**
- **DTO** (Data Transfer Object): Estructura que viene de la API/BD
- **Entity/Model**: Estructura limpia que usa la app internamente

#### **c) Data Sources**
Acceso directo a las fuentes de datos.
```kotlin
// ProductRemoteDataSource.kt (API)
class ProductRemoteDataSource @Inject constructor(
    private val api: ProductApiService
) {
    suspend fun getProducts(): List<Product> {
        val dtos = api.getProducts()
        return dtos.map { it.toDomain() }
    }
}

// ProductLocalDataSource.kt (Room/DataStore)
class ProductLocalDataSource @Inject constructor(
    private val dao: ProductDao
) {
    suspend fun getProducts(): List<Product> {
        val entities = dao.getAllProducts()
        return entities.map { it.toDomain() }
    }
}
```

---

### 4. **DI Layer** (`di/`) - Dependency Injection con Hilt
**Responsabilidad:** Proveer dependencias a toda la app.
```kotlin
// AppModule.kt
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.example.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    
    @Provides
    @Singleton
    fun provideProductApiService(retrofit: Retrofit): ProductApiService {
        return retrofit.create(ProductApiService::class.java)
    }
    
    @Provides
    @Singleton
    fun provideProductRepository(
        remoteDataSource: ProductRemoteDataSource,
        localDataSource: ProductLocalDataSource
    ): ProductRepository {
        return ProductRepositoryImpl(remoteDataSource, localDataSource)
    }
}
```

**¿Qué hace Hilt?**
- Crea instancias de clases automáticamente
- Gestiona el ciclo de vida (Singleton, ViewModel scoped, etc.)
- Inyecta dependencias usando `@Inject`

---

### 5. **Navigation Layer** (`navigation/`)
**Responsabilidad:** Gestionar la navegación entre pantallas.

#### **Estructura del proyecto del profesor:**
```
navigation/
├── navGraph/
│   ├── AuthGraph.kt      # Flujo de autenticación (login, registro)
│   └── HomeGraph.kt      # Flujo principal de la app
├── AppNav.kt             # NavHost principal
├── HandleNavigationEvents.kt
├── Route.kt              # Definición de rutas
└── UiEvent.kt            # Eventos de navegación
```

**Ejemplo:**
```kotlin
// Route.kt
sealed class Route(val route: String) {
    object Login : Route("login")
    object Home : Route("home")
    object Profile : Route("profile/{userId}") {
        fun createRoute(userId: String) = "profile/$userId"
    }
}

// AuthGraph.kt
fun NavGraphBuilder.authGraph(navController: NavController) {
    navigation(
        startDestination = Route.Login.route,
        route = "auth"
    ) {
        composable(Route.Login.route) {
            LoginScreen(
                onNavigateToHome = {
                    navController.navigate(Route.Home.route) {
                        popUpTo("auth") { inclusive = true }
                    }
                }
            )
        }
    }
}

// AppNav.kt
@Composable
fun AppNav() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        authGraph(navController)
        homeGraph(navController)
    }
}
```

---

## Flujo completo de datos
```
User interactúa con UI
        ↓
    UI (Composable)
        ↓
    ViewModel (gestiona estado)
        ↓
    Use Case (lógica de negocio)
        ↓
    Repository Interface (definición)
        ↓
    Repository Implementation (decide fuente de datos)
        ↓
    Data Source (API / Database)
```

---

## Ventajas de esta arquitectura

1. **Separación de responsabilidades**: Cada capa tiene un propósito claro
2. **Testeable**: Puedes testear cada capa independientemente
3. **Mantenible**: Cambios en una capa no afectan a las demás
4. **Escalable**: Fácil añadir nuevas features
5. **Reutilizable**: Use cases y repositorios se pueden usar en múltiples ViewModels

---

## Principios SOLID aplicados

- **Single Responsibility**: Cada clase tiene una sola responsabilidad
- **Open/Closed**: Abierto a extensión, cerrado a modificación
- **Liskov Substitution**: Puedes cambiar implementaciones sin romper el código
- **Interface Segregation**: Interfaces pequeñas y específicas
- **Dependency Inversion**: Dependemos de abstracciones (interfaces), no de implementaciones

---

## ¿Por qué usar Hilt?

Sin Hilt tendrías que hacer esto manualmente:
```kotlin
//val api = Retrofit.Builder()...build().create(ApiService::class.java)
val remoteDataSource = ProductRemoteDataSource(api)
val localDataSource = ProductLocalDataSource(dao)
val repository = ProductRepositoryImpl(remoteDataSource, localDataSource)
val useCase = GetProductsUseCase(repository)
val viewModel = ProductViewModel(useCase)
```

Con Hilt solo haces:
```kotlin
//@Inject constructor(private //val useCase: GetProductsUseCase)
```

Y Hilt se encarga del resto automáticamente.

---

## Resumen rápido

| Capa | Responsabilidad | Ejemplo |
|------|----------------|---------|
| **UI** | Mostrar datos y capturar eventos | LoginScreen.kt, LoginViewModel.kt |
| **Domain** | Lógica de negocio | LoginUseCase.kt, User.kt (entity) |
| **Data** | Obtener/guardar datos | UserRepositoryImpl.kt, UserDto.kt |
| **DI** | Proveer dependencias | AppModule.kt |
| **Navigation** | Gestionar navegación | AuthGraph.kt, AppNav.kt |

---

## Siguiente paso

Ahora que entiendes la arquitectura, el siguiente paso es:
1. Configurar Hilt en el proyecto
2. Crear la estructura de carpetas
3. Migrar cada pantalla paso a paso

¿Listo para empezar la migración?