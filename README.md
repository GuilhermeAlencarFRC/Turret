# 🎯 FRC Turret

Sistema de **torreta rotativa para robôs FRC**, desenvolvido com **WPILib 2026**, **REV Spark MAX** e uma arquitetura baseada em comandos para proporcionar controle preciso, seguro e modular.

O projeto foi desenvolvido pensando em uma torreta capaz de realizar **controle manual por joystick**, posicionamento através de alvo angular e gerenciamento de limites mecânicos, mantendo a lógica de controle separada da camada de entrada do operador.

---

## 🚀 Visão geral

A torreta funciona através de um sistema de controle em malha fechada.

O operador fornece uma entrada pelo joystick:

~~~text
Xbox Controller
       │
       ▼
   LuverLib
       │
       ▼
TurretManualCommand
       │
       ▼
 TurretSubsystem
       │
       ├── Target Angle
       ├── Limits
       ├── Safety Logic
       └── PID Control
       │
       ▼
   REV Spark MAX
       │
       ▼
   Turret Motor
~~~

O joystick **não controla diretamente a potência do motor**.

Em vez disso, sua entrada é utilizada para modificar o **ângulo desejado da torreta**, enquanto o subsistema é responsável por levar o mecanismo até esse alvo.

---

# ⚙️ Principais recursos

- 🎮 Controle manual através de Xbox Controller
- 🧩 Integração com **LuverLib**
- 🔄 Controle de posição da torreta
- 🎯 Controle baseado em ângulo alvo
- 🔒 Limitação de movimento
- 🛡️ Proteções contra movimentos fora da faixa permitida
- 📐 Controle de posição utilizando encoder
- ⚡ Controle através de REV Spark MAX
- 🧠 Arquitetura baseada em WPILib Command-Based
- 🔧 Constantes centralizadas
- 🧱 Separação entre comando, subsistema e hardware
- 🕹️ Deadzone aplicada ao joystick
- 🔁 Estrutura preparada para futuras funções automáticas de tracking

---

# 🧠 Arquitetura

O projeto utiliza a arquitetura **Command-Based da WPILib**.

### `RobotContainer`

Responsável por:

- Inicializar a torreta
- Inicializar o controle
- Configurar os bindings
- Definir os comandos da torreta

Estrutura:

~~~text
RobotContainer
├── TurretSubsystem
├── XboxController
└── TurretManualCommand
~~~

---

### `TurretSubsystem`

É o núcleo do sistema.

Responsável por:

- Controle do motor
- Leitura do encoder
- Gerenciamento do ângulo atual
- Definição do alvo
- Controle PID
- Limites da torreta
- Segurança do movimento

O operador não precisa conhecer os detalhes do motor ou do encoder.

Ele apenas fornece uma intenção:

~~~text
"Quero mover a torreta nessa direção."
~~~

O subsistema transforma essa intenção em movimento físico.

---

### `TurretManualCommand`

Responsável pela interface entre o operador e o subsistema.

A entrada do joystick é obtida através da **LuverLib**:

~~~java
double joystickValue =
    controller.getLeftAnalogLeftRightReading();
~~~

Essa abordagem evita espalhar chamadas como `getRawAxis()` pelo projeto.

---

# 🎮 Controle

O sistema utiliza um **Xbox Controller** através do wrapper da LuverLib.

~~~text
Xbox Controller
      │
      ▼
LuverLib XboxController
      │
      ▼
TurretManualCommand
      │
      ▼
TurretSubsystem
~~~

O controle horizontal do analógico esquerdo é utilizado para comandar a torreta.

### Deadzone

Uma deadzone é aplicada para evitar pequenos movimentos causados por ruído ou imperfeições físicas do joystick.

~~~text
       Deadzone
         │
         ▼
<─────── 0 ───────>
~~~

Quando o valor está dentro da deadzone, a entrada é tratada como:

~~~java
0.0
~~~

---

# 🔄 Controle por alvo

A torreta trabalha com um **ângulo alvo**.

Conceitualmente:

~~~text
Joystick
   │
   ▼
Entrada do operador
   │
   ▼
Alteração do Target Angle
   │
   ▼
PID Controller
   │
   ▼
Motor
~~~

Isso permite que o controle seja mais previsível do que simplesmente aplicar potência diretamente ao motor.

O sistema pode, por exemplo:

- aumentar o ângulo alvo;
- diminuir o ângulo alvo;
- respeitar limites;
- impedir comandos perigosos;
- corrigir automaticamente a posição através do PID.

---

# 🛡️ Segurança e limites

Como uma torreta possui movimento rotacional e pode atingir componentes do próprio robô, o controle possui lógica para impedir que o mecanismo ultrapasse os limites definidos.

O fluxo conceitual é:

~~~text
Target solicitado
       │
       ▼
Validação do limite
       │
   ┌───┴───┐
   │       │
 Seguro   Fora
   │       │
   ▼       ▼
Executa   Corrige
~~~

Isso permite que a camada de controle continue recebendo comandos do operador sem permitir que o mecanismo simplesmente ignore suas restrições físicas.

---

# ⚡ Hardware

O projeto foi desenvolvido para utilização de componentes de controle **REV Robotics**.

### Motor Controller

**REV Spark MAX**

Responsável pelo acionamento do motor da torreta.

### Encoder

O encoder é utilizado para determinar a posição da torreta e permitir o controle baseado em posição.

### Controller

**Xbox Controller**

Utilizado como interface principal do operador.

---

# 💻 Software

| Tecnologia | Utilização |
|---|---|
| **Java** | Linguagem principal |
| **WPILib 2026** | Framework FRC |
| **REVLib** | Controle dos Spark MAX |
| **LuverLib** | Abstração do controle Xbox |
| **GradleRIO** | Build e deploy |
| **Git** | Controle de versão |

---

# 📁 Estrutura do projeto

~~~text
src/
└── main/
    └── java/
        └── frc/
            └── robot/
                ├── commands/
                │   └── TurretManualCommand.java
                │
                ├── subsystems/
                │   └── TurretSubsystem.java
                │
                ├── luverlib/
                │   └── controller/
                │       ├── XboxController.java
                │       └── XboxConstants.java
                │
                ├── Robot.java
                ├── RobotContainer.java
                └── Constants.java
~~~

A estrutura busca manter cada responsabilidade isolada:

~~~text
Controller
    ↓
Command
    ↓
Subsystem
    ↓
Motor Controller
    ↓
Motor
~~~

---

# 🔧 Configuração

Antes de utilizar o projeto em um robô, configure os IDs dos dispositivos nas constantes correspondentes.

Exemplo:

~~~java
public static final int TURRET_MOTOR_ID = ...;
~~~

Também devem ser configurados os parâmetros relacionados a:

- limites angulares;
- velocidade;
- PID;
- conversão do encoder;
- redução mecânica;
- direção do motor;
- corrente máxima;
- comportamento do Spark MAX.

> ⚠️ Os valores utilizados neste projeto dependem diretamente da montagem mecânica da torreta. Sempre valide os limites antes de operar o mecanismo em velocidade.

---

# 📐 Redução mecânica

O sistema de controle deve considerar a relação entre o motor e a torreta.

Por exemplo, se:

~~~text
10 rotações do motor
        =
1 rotação da torreta
~~~

a redução mecânica é:

~~~text
10:1
~~~

Essa relação precisa ser considerada quando o encoder do motor é convertido para a posição angular real da torreta.

---

# 🧪 Testes

Antes de colocar o sistema em operação completa, recomenda-se testar progressivamente.

### 1. Motor

Verificar:

- direção;
- sentido positivo/negativo;
- corrente;
- comportamento do Spark MAX.

### 2. Encoder

Verificar:

- leitura;
- direção;
- posição inicial;
- conversão para ângulo.

### 3. Limites

Testar:

~~~text
Limite esquerdo
      ↓
     STOP
      ↑
Movimento normal
      ↓
     STOP
      ↑
Limite direito
~~~

### 4. Controle manual

Somente após os testes anteriores:

- baixa velocidade;
- baixa aceleração;
- região segura;
- operador preparado para interromper o robô.

---

# 🧩 Possíveis evoluções

A arquitetura permite adicionar funcionalidades sem modificar completamente o sistema atual.

Algumas possibilidades:

- 🎯 Tracking automático de alvo
- 📷 Integração com câmera
- 🟢 Limelight
- 🤖 Controle automático baseado em visão
- 🧭 Integração com odometria
- 📡 Controle baseado em posição do robô
- 🔄 Auto-aim
- 📊 Telemetria no SmartDashboard / AdvantageScope
- 🧠 Feedforward + PID
- ⚙️ Motion profiling

Uma possível evolução seria:

~~~text
                 ┌──────────────┐
Joystick ───────►│              │
                 │    Turret    │────► Motor
Vision ─────────►│  Controller  │
                 │              │
Odometry ───────►│              │
                 └──────────────┘
~~~

Assim, a mesma torreta poderia receber comandos de diferentes fontes sem precisar reescrever toda a lógica de controle.

---

# 🏆 FRC

Este projeto faz parte do desenvolvimento de sistemas para **FIRST Robotics Competition (FRC)**, com foco em controle de mecanismos, programação embarcada e integração entre software e hardware.

O objetivo é aplicar conceitos de:

- Engenharia de software
- Controle de sistemas
- Automação
- Robótica
- Sistemas embarcados
- Eletrônica
- Controle de motores

---

# 👨‍💻 Desenvolvido por

**Guilherme Alencar Nienkoetter**

FRC Programmer · Robotics · Industrial Automation · Software

---

## 📜 License

Este projeto está disponível para fins educacionais e de desenvolvimento em robótica.

Consulte os arquivos do repositório para informações específicas sobre licenciamento e utilização.

---

> **Build. Test. Iterate. Compete. 🤖**
