# ISW321 Ecosystem Simulator

## Documentación Técnica

### 1. Arquitectura General

El proyecto implementa una **arquitectura en capas** (Layered Architecture) inspirada en principios de **Domain-Driven Design (DDD)** y **Clean Architecture**, organizando el código en cuatro capas principales:

#### 1.1 Capa de Presentación (Presentation Layer)
Gestiona la interfaz de usuario mediante **JavaFX** con el patrón **FXML**. Los controladores actúan como adaptadores que conectan las vistas con la lógica de aplicación, manteniendo la separación entre la interfaz gráfica y la lógica de negocio. Esta capa es responsable de renderizar el ecosistema, capturar eventos del usuario y actualizar la visualización en tiempo real.

#### 1.2 Capa de Aplicación (Application Layer)
Orquesta los casos de uso del sistema y coordina el flujo de datos entre capas. Se subdivide en:

- **Contexts**: Implementan el patrón **Singleton** para mantener el estado global de cada módulo (Login, Registro, Simulación, Sesión). Utilizan JavaFX Properties para binding reactivo con la interfaz.
- **Handlers**: Procesan las operaciones de negocio específicas (autenticación, registro de usuarios, configuración de simulaciones). Aplican validaciones y transformaciones de datos mediante schemas.
- **Services**: Proporcionan servicios transversales como gestión de escenas, manipulación de matrices y operaciones auxiliares.
- **Helpers**: Utilitarios para funciones comunes (hashing de contraseñas, validación de datos, generación de números aleatorios, envío de correos).

#### 1.3 Capa de Dominio (Domain Layer)
Contiene la lógica de negocio central del simulador de ecosistemas, libre de dependencias externas. Se estructura en:

- **Core**: Motor de simulación con componentes especializados:
  - **Objects**: Entidades fundamentales (Animal, Matrix, Sector, Coord, Action, Result) que representan el estado del ecosistema.
  - **Behaviors**: Implementan el patrón **Strategy** para definir comportamientos animales (Carnívoro, Herbívoro, Omnívoro, Zombie). Cada comportamiento encapsula reglas específicas de supervivencia, reproducción y alimentación.
  - **Attempts**: Representan intentos de acciones (movimiento, caza, reproducción, muerte) aplicando el patrón **Command**.
  - **Factories**: Patrón **Factory Method** para la creación de animales con configuraciones específicas.
  - **Setup**: Componentes de inicialización y ejecución del simulador (Config, Context, Engine, Initializer, Populator, Renderer).
  - **Artifacts**: Objetos auxiliares para reportes y detalles de simulación.

- **Models**: Entidades de negocio (Customer, Ecosystem) que representan usuarios y configuraciones de ecosistemas guardados.
- **Enums**: Enumeraciones que definen tipos constantes (Balance, Category, FXMLPath, MailType).

#### 1.4 Capa de Infraestructura (Infrastructure Layer)
Gestiona la persistencia de datos y servicios externos. Implementa el patrón **Repository** para abstraer el acceso a datos:

- **Repositories**: Interfaces que definen contratos de persistencia (CustomerRepository, EcosystemRepository, ActionRepository).
- **Database**: Implementaciones concretas usando almacenamiento en archivos de texto con formato TLQ (Tag-Line-Query). BaseTLQRepository proporciona funcionalidad base para serialización/deserialización.

---

### 2. Patrones de Diseño Implementados

#### 2.1 Patrones Creacionales
- **Singleton**: Utilizado en Contexts (LoginContext, SimulationContext, SimulatorContext) para garantizar una única instancia de estado global.
- **Factory Method**: AnimalFactory crea instancias de animales con comportamientos específicos según configuración.

#### 2.2 Patrones Estructurales
- **Repository**: Abstrae la lógica de persistencia, permitiendo cambiar el mecanismo de almacenamiento sin afectar otras capas.
- **Layer Supertype**: BaseTLQRepository proporciona funcionalidad común para todas las implementaciones de repositorios.

#### 2.3 Patrones de Comportamiento
- **Strategy**: Los diferentes Behavior (CarnivoreBehavior, HerbivoreBehavior, etc.) permiten cambiar dinámicamente el comportamiento de los animales.
- **Command**: Los Attempts encapsulan acciones como objetos ejecutables, facilitando el historial y la reversibilidad de acciones.
- **Observer**: JavaFX Properties implementan observadores para actualización reactiva de la interfaz.

---

### 3. Lógica del Motor de Simulación

El simulador ejecuta un ciclo de turnos donde cada animal realiza acciones basadas en su comportamiento asignado:

#### 3.1 Ciclo de Ejecución
El **SimulatorEngine** coordina la ejecución mediante un Timeline de JavaFX que ejecuta turnos a intervalos regulares. En cada turno, los animales actúan secuencialmente según prioridades de especies configuradas.

#### 3.2 Sistema de Comportamientos
Cada animal posee un Behavior que determina sus acciones mediante un sistema de prioridades:
1. **Muerte**: Evalúa condiciones de supervivencia (hambre, edad).
2. **Reproducción**: Intenta reproducirse si cumple condiciones (madurez, pareja cercana).
3. **Caza/Alimentación**: Busca presas o recursos según su categoría alimentaria.
4. **Movimiento**: Se desplaza a sectores adyacentes si no puede realizar otras acciones.
5. **Inactividad**: Permanece inmóvil si no hay acciones disponibles.

#### 3.3 Sistema de Intentos (Attempts)
Cada acción se ejecuta mediante un Attempt que evalúa precondiciones, ejecuta la lógica y retorna un Result con información detallada:
- **AttemptSex**: Verifica proximidad de pareja, genera descendencia con mutaciones posibles.
- **AttemptHunt**: Busca presas válidas en sectores adyacentes, calcula éxito basado en estadísticas.
- **AttemptMove**: Encuentra sectores vacíos cercanos para desplazamiento.
- **AttemptDeath**: Evalúa condiciones letales y ejecuta eliminación del animal.

#### 3.4 Gestión de Matriz y Sectores
La **Matrix** representa el espacio bidimensional del ecosistema dividido en **Sectors**. Cada Sector puede contener un Animal o estar vacío. El MatrixService proporciona operaciones para consultar sectores adyacentes, validar coordenadas y gestionar ocupación espacial.

---

### 4. Gestión de Estado y Contexto

#### 4.1 Contextos de Aplicación
Los Contexts mantienen el estado mutable de cada módulo usando JavaFX Properties, permitiendo binding bidireccional con la UI y reactividad automática ante cambios.

#### 4.2 SimulatorContext
Almacena el estado completo del ecosistema actual:
- Colecciones de animales organizadas por especie y categoría.
- Matriz espacial con referencias a sectores ocupados.
- Configuraciones de balance ecológico y flags especiales (mutaciones zombie, expansión omnívora).
- Historial de acciones para generación de reportes.

---

### 5. Persistencia de Datos

El sistema implementa un formato de archivo de texto propietario (TLQ) estructurado en bloques delimitados. Cada bloque contiene pares clave-valor que representan propiedades de entidades. El BaseTLQRepository proporciona métodos comunes para lectura/escritura, mientras que los repositorios específicos implementan la lógica de mapeo entre objetos de dominio y representación textual.

---

### 6. Validación y Seguridad

- **Validación de Entrada**: ValidatorHelper implementa reglas de validación para emails, cédulas y contraseñas.
- **Hashing de Contraseñas**: HashingHelper utiliza algoritmos seguros para almacenamiento de credenciales.
- **Schemas**: Objetos de transferencia de datos que validan y sanitizan información antes de procesamiento.