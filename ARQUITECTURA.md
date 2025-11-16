# DOCUMENTACIÓN DE ESTRUCTURA DEL PROYECTO - DeepCode App

## Arquitectura General

**DeepCode** es una aplicación Android desarrollada con **Clean Architecture** siguiendo el patrón **MVVM** (Model-View-ViewModel). La estructura está organizada en capas claramente separadas para facilitar el mantenimiento y la escalabilidad.

---

## Estructura de Carpetas Principal
```
com.deepcodeia.deepcode_app/
├── data/           → Capa de datos (API, DTOs, Repositorios)
├── di/             → Inyección de dependencias (Hilt)
├── domain/         → Capa de dominio (Modelos, Repositorios, Use Cases)
├── navigation/     → Navegación entre pantallas
├── ui/             → Capa de presentación (Screens, ViewModels, Theme)
└── MainActivity.kt → Actividad principal de la app
```

---

## CAPA DATA (`data/`)

**Responsabilidad:** Maneja la comunicación con fuentes de datos externas (API REST) y la persistencia local.

### data/remote/apiservice/
Interfaces Retrofit que definen los endpoints de la API.

| Archivo | Descripción |
|---------|-------------|
| `AuthApiService.kt` | Endpoints de autenticación (login, register) |
| `ChallengeApiService.kt` | Endpoints de retos (CRUD, filtros) |
| `ProgressApiService.kt` | Endpoints de progreso del usuario |
| `UserApiService.kt` | Endpoints de información de usuario |

---

### data/remote/dto/
Data Transfer Objects - Objetos para transferir datos entre frontend y backend.

| Archivo | Descripción |
|---------|-------------|
| `AuthResponse.kt` | Respuesta de login/register con token JWT |
| `ChallengeDto.kt` | Estructura de un reto del backend |
| `CreateChallengeRequest.kt` | Request para crear un nuevo reto |
| `LoginRequest.kt` | Credenciales de login (email, password) |
| `MarkChallengeRequest.kt` | Request para marcar reto como completado |
| `RegisterRequest.kt` | Datos de registro de nuevo usuario |
| `UserChallengeDto.kt` | Progreso de un reto por el usuario |
| `UserDto.kt` | Información básica del usuario |
| `UserProgressDto.kt` | Progreso completo del usuario |

---

### data/remote/interceptors/
Interceptores HTTP para modificar peticiones automáticamente.

| Archivo | Descripción |
|---------|-------------|
| `JwtInterceptor.kt` | Añade token JWT automáticamente a todas las peticiones protegidas |

---

### data/repository/
Implementaciones concretas de los repositorios (acceso a datos mediante Retrofit).

| Archivo | Descripción |
|---------|-------------|
| `AuthRepositoryImpl.kt` | Implementa login y register con el backend |
| `ChallengeRepositoryImpl.kt` | Implementa CRUD de retos y consultas |
| `ProgressRepositoryImpl.kt` | Implementa operaciones de progreso del usuario |
| `UserRepositoryImpl.kt` | Implementa consultas de información del usuario |

---

### data/
Otros componentes de datos.

| Archivo | Descripción |
|---------|-------------|
| `LoginDataStore.kt` | Persistencia local del token JWT usando DataStore |

---

## CAPA DI (`di/`)

**Responsabilidad:** Configuración de inyección de dependencias con Hilt.

| Archivo | Descripción |
|---------|-------------|
| `ApiModule.kt` | Provee instancias de ApiServices (Retrofit) |
| `AppModule.kt` | Provee dependencias generales (Context) |
| `NetworkModule.kt` | Configura Retrofit, OkHttp, interceptores |
| `RepositoryModule.kt` | Vincula interfaces de repositorios con implementaciones |

---

## CAPA DOMAIN (`domain/`)

**Responsabilidad:** Contiene la lógica de negocio pura, independiente de frameworks.

### domain/model/
Entidades del dominio (objetos puros sin dependencias de Android/Retrofit).

| Archivo | Descripción |
|---------|-------------|
| `Challenge.kt` | Modelo de un reto de programación |
| `User.kt` | Modelo de usuario |
| `UserProgress.kt` | Modelo de progreso de un reto |

---

### domain/repository/
Interfaces que definen contratos (QUÉ se puede hacer, no CÓMO).

| Archivo | Descripción |
|---------|-------------|
| `AuthRepository.kt` | Contrato de operaciones de autenticación |
| `ChallengeRepository.kt` | Contrato de operaciones de retos |
| `ProgressRepository.kt` | Contrato de operaciones de progreso |
| `UserRepository.kt` | Contrato de operaciones de usuario |

---

### domain/usecase/
Casos de uso - Encapsulan la lógica de negocio (una acción del usuario = un UseCase).

#### domain/usecase/auth/
| Archivo | Descripción |
|---------|-------------|
| `LoginUseCase.kt` | Ejecuta el login del usuario |
| `RegisterUseCase.kt` | Ejecuta el registro de nuevo usuario |

#### domain/usecase/challenge/
| Archivo | Descripción |
|---------|-------------|
| `CreateChallengeUseCase.kt` | Crea un nuevo reto |
| `DeleteChallengeUseCase.kt` | Elimina un reto (solo el creador) |
| `GetChallengesUseCase.kt` | Obtiene lista de retos con filtros |
| `GetMyCreatedChallengesUseCase.kt` | Obtiene retos creados por el usuario |
| `MarkChallengeAsCompletedUseCase.kt` | Marca un reto como completado |

#### domain/usecase/user/
| Archivo | Descripción |
|---------|-------------|
| `GetCurrentUserUseCase.kt` | Obtiene información del usuario autenticado |
| `GetUserProgressUseCase.kt` | Obtiene progreso completo del usuario |

---

## CAPA NAVIGATION (`navigation/`)

**Responsabilidad:** Maneja la navegación entre pantallas.

### navigation/
| Archivo | Descripción |
|---------|-------------|
| `Route.kt` | Define todas las rutas de navegación (sealed class) |
| `UiEvent.kt` | Eventos de navegación (Navigate, NavigateBack, ShowSnackbar) |
| `HandleNavigationEvents.kt` | Función composable para manejar eventos de navegación |
| `AppNavGraph.kt` | NavHost principal que define el grafo de navegación |

### navigation/navGraph/
| Archivo | Descripción |
|---------|-------------|
| `AuthGraph.kt` | Subgrafo de navegación de autenticación (Login, Register) |
| `HomeGraph.kt` | Subgrafo de navegación del flujo principal (Home, Profile, Challenges, etc.) |

---

## CAPA UI (`ui/`)

**Responsabilidad:** Presenta la información al usuario (Jetpack Compose).

### ui/components/
Componentes reutilizables de UI.

| Archivo | Descripción |
|---------|-------------|
| `AppButton.kt` | Botón personalizado con estilo de la app |

---

### ui/screens/
Pantallas de la aplicación. Cada pantalla sigue el patrón:
- **Entry.kt** → Conecta ViewModel con Screen
- **Screen.kt** → UI pura (Composables)
- **UiState.kt** → Estado de la pantalla
- **ViewModel.kt** → Lógica de negocio y manejo de estado

#### ui/screens/auth/login/
| Archivo | Descripción |
|---------|-------------|
| `LoginEntry.kt` | Punto de entrada de la pantalla de login |
| `LoginScreen.kt` | UI de la pantalla de login |
| `LoginUiState.kt` | Estado de la pantalla (email, password, errores) |
| `LoginViewModel.kt` | Lógica de login y validaciones |

#### ui/screens/auth/register/
| Archivo | Descripción |
|---------|-------------|
| `RegisterEntry.kt` | Punto de entrada de la pantalla de registro |
| `RegisterScreen.kt` | UI de la pantalla de registro |
| `RegisterUiState.kt` | Estado de la pantalla (username, email, password, errores) |
| `RegisterViewModel.kt` | Lógica de registro y validaciones |

#### ui/screens/challenges/
| Archivo | Descripción |
|---------|-------------|
| `ChallengesEntry.kt` | Punto de entrada de lista de retos |
| `ChallengesScreen.kt` | UI con lista de retos y filtros |
| `ChallengesUiState.kt` | Estado (retos, filtros, completados) |
| `ChallengesViewModel.kt` | Lógica de carga y filtrado de retos |

#### ui/screens/completedchallenges/
| Archivo | Descripción |
|---------|-------------|
| `CompletedChallengesEntry.kt` | Punto de entrada de retos completados |
| `CompletedChallengesScreen.kt` | UI de retos completados con filtros |
| `CompletedChallengesUiState.kt` | Estado de retos completados |
| `CompletedChallengesViewModel.kt` | Lógica de carga de retos completados |

#### ui/screens/createchallenge/
| Archivo | Descripción |
|---------|-------------|
| `CreateChallengeEntry.kt` | Punto de entrada de crear reto |
| `CreateChallengeScreen.kt` | UI del formulario de creación |
| `CreateChallengeUiState.kt` | Estado del formulario (título, descripción, validaciones) |
| `CreateChallengeViewModel.kt` | Lógica de creación y validaciones |

#### ui/screens/home/
| Archivo | Descripción |
|---------|-------------|
| `HomeScreen.kt` | Pantalla principal con tiles de navegación |

#### ui/screens/mycreatedchallenges/
| Archivo | Descripción |
|---------|-------------|
| `MyCreatedChallengesEntry.kt` | Punto de entrada de mis retos creados |
| `MyCreatedChallengesScreen.kt` | UI de retos creados por el usuario con opción de eliminar |
| `MyCreatedChallengesUiState.kt` | Estado de mis retos creados |
| `MyCreatedChallengesViewModel.kt` | Lógica de carga y eliminación de retos |

#### ui/screens/profile/
| Archivo | Descripción |
|---------|-------------|
| `ProfileEntry.kt` | Punto de entrada del perfil |
| `ProfileScreen.kt` | UI del perfil con progreso y botones de navegación |
| `ProfileUiState.kt` | Estado del perfil (username, email, progreso) |
| `ProfileViewModel.kt` | Lógica de carga de datos del usuario |

####  ui/screens/splash/
| Archivo | Descripción |
|---------|-------------|
| `SplashEntry.kt` | Punto de entrada de splash/auto-login |
| `SplashScreen.kt` | UI de pantalla de carga inicial |
| `SplashViewModel.kt` | Lógica de verificación de token y auto-login |

---

###  ui/theme/
Configuración del tema visual de la app.

| Archivo | Descripción |
|---------|-------------|
| `Color.kt` | Paleta de colores personalizada (verde neón, azul eléctrico) |
| `Dimens.kt` | Dimensiones reutilizables |
| `Shape.kt` | Formas personalizadas |
| `Theme.kt` | Configuración del MaterialTheme |
| `Type.kt` | Tipografía de la app |

---

###  ui/
Archivos raíz de UI.

| Archivo | Descripción |
|---------|-------------|
| `DeepCodeApplication.kt` | Application class con configuración de Hilt |
| `MainActivity.kt` | Actividad principal que aloja el NavHost |

---

## FLUJO DE DATOS (EJEMPLO: Marcar reto como completado)
```
1. Usuario pulsa botón "Marcar Completado" en ChallengesScreen
   ↓
2. ChallengesViewModel.onMarkAsCompleted(challengeId)
   ↓
3. MarkChallengeAsCompletedUseCase(challengeId)
   ↓
4. ProgressRepository.markChallengeAsCompleted(challengeId)
   ↓
5. ProgressRepositoryImpl → Retrofit POST /progress
   ↓
6. JwtInterceptor añade token JWT automáticamente
   ↓
7. Backend valida y actualiza BD
   ↓
8. Response 200 OK
   ↓
9. ViewModel actualiza estado (añade ID a completados)
   ↓
10. ChallengesScreen se recompone con animación
    ↓
11. Card cambia a verde oscuro + check desaparece
```

---

## TECNOLOGÍAS UTILIZADAS

### Backend Integration:
- Retrofit 2.9.0 - Cliente HTTP
- OkHttp 4.x - Cliente HTTP bajo nivel
- Gson 2.10.1 - Parseo JSON

### Dependency Injection:
- Hilt 2.48 - DI con Dagger

### Persistencia Local:
- DataStore - Almacenamiento de token JWT

### UI:
- Jetpack Compose - UI declarativa
- Material 3 - Componentes visuales
- Navigation Compose - Navegación entre pantallas

### Arquitectura:
- MVVM - Patrón de presentación
- Clean Architecture - Separación en capas
- Repository Pattern - Abstracción de datos
- Use Cases - Lógica de negocio encapsulada

---

## ESTADÍSTICAS DEL PROYECTO

- **Total de pantallas:** 9
- **Total de UseCases:** 11
- **Total de Repositorios:** 4
- **Total de ApiServices:** 4
- **Endpoints conectados:** 12+
- **Líneas de código:** ~4000+

---

## CARACTERÍSTICAS DESTACADAS

### Arquitectura:
- Clean Architecture profesional
- Separación clara de capas (data, domain, ui)
- Repository Pattern para abstracción de datos
- Use Cases para lógica de negocio

### Networking:
- JWT Authentication automático
- Interceptor para añadir token
- Manejo robusto de errores HTTP
- DTOs bien estructurados

### UI/UX:
- Jetpack Compose moderno
- Animaciones fluidas
- Tema personalizado (verde neón + azul eléctrico)
- Navegación compleja con NavGraph
- Filtros avanzados

### Funcionalidades:
- Sistema de autenticación completo
- CRUD de retos
- Sistema de progreso del usuario
- Auto-login con DataStore
- Filtros por lenguaje, nivel y estado

---

## 🎓 CONCLUSIÓN

**DeepCode** es una aplicación full-stack completa que demuestra:
- Conocimientos sólidos de Kotlin y Android moderno
- Comprensión de arquitecturas escalables
- Capacidad de integración frontend-backend
- Atención al detalle en UX/UI
- Código limpio y bien organizado

---

**Desarrollado por:** Alex  
**Fecha:** Marzo 2026  
**Tecnología:** Android (Kotlin) + Spring Boot  
**Arquitectura:** Clean Architecture + MVVM