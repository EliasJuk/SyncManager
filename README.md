# SyncManager

``` mermaid
classDiagram

Colaborador
Colaborador : - nome String
Colaborador : - createdAt Data
Colaborador : + gets()
Colaborador : + sets()


Colaborador ..o Funcao
Colaborador ..o BU
Colaborador "1" ..o "1..*" Setor

User
User : - user  String
User : - senha int
User : - createdAt Data
User : + gets()
User : + sets()

BU
BU : - nome String
BU : - createdAt Data
BU : + gets()
BU : + sets()

BU "1..*" <|.. "1" Setor
Setor
Setor : - nome String
Setor : - createdAt Data
Setor : + gets()
Setor : + sets()

Funcao
Funcao : - nome String
Funcao : - createdAt Data
Funcao : + gets()
Funcao : + sets()

```

``` mermaid
classDiagram
componentes
componentes : - CTF String

circuitos
circuitos : - CTF String

componentes o.. circuitos_componentes
circuitos o.. circuitos_componentes
circuitos_componentes
circuitos_componentes : - CTF String
circuitos_componentes : - id_circuitos int
circuitos_componentes : - id_componente int

```

---