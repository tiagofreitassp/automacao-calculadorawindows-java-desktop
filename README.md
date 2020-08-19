# automacao-calculadorawindows-java-desktop
Script de automacao de fazer calculos na calculadora do windows usando Java, Winium, Selenium e jUnit.

### Cobertura dos testes:  ###

* Realizar compra online

## Tecnologias:
* [Java JDK 8](https://www.oracle.com/br/java/technologies/javase-downloads.html)
* [Maven](https://maven.apache.org)
* [Maven dependencias](https://mvnrepository.com)
* [Selenium Webdriver](https://www.selenium.dev/projects/)
* [Intellij Idea](https://www.jetbrains.com/pt-br/idea/)
* [jUnit](https://junit.org/junit5/)
* [Winium](https://github.com/2gis/Winium)

## Dependências:
* Selenium-java
* jUnit 4
* Commons-io
* Cucumber-junit
* Cucumber-java
* Jxl
* Poi-ooxml
* Winium

## Instruções de execução:

###  - Plataforma
*Importante: 
O projeto foi criado para ser executado apenas no Windows.

Os metodos para criar o driver Winium estao na classe calculadoraWinCore.

Recomendado utilizar o Intellij Idea, mas pode usar o Eclipse IDE, Visual Studio Code ou Spring Tools Suite.

###  - Fluxo
*Descricao: Este script ira executar uma compra online, seguindo o fluxo desde a escolha do produto ate a etapa de confirmacao da compra.

###  - Massas
*Descricao: Nao e necessario criar massas por enquanto.

###  - Evidencias
*Descricao:
Apos a execucao as imagens de evidencias sao armazenadas na pasta screenshots.

Para visualizar as evidencias no documento pode usar o MS Office Word ou o LibreOffice

###  - Inicializar a automação
*Descricao:

Abrir uma das classes ***Test.java no Intellij Idea ou no Eclipse.

No caso para executar numa versao diferente do windows 10, verifique se a calculadora esta no mesmo caminho da calculadora do Windows 10.