# automacao-calculadorawindows-java-desktop
Script de automacao de fazer calculos na calculadora do windows usando Java, Winium, Selenium e jUnit.

### Cobertura dos testes:  ###

* Realizar calculos basicos

## Tecnologias:
* [Java JDK 8](https://www.oracle.com/br/java/technologies/javase-downloads.html)
* [Maven](https://maven.apache.org)
* [Maven dependencias](https://mvnrepository.com)
* [Selenium Webdriver](https://www.selenium.dev/projects/)
* [Eclipse IDE](https://www.eclipse.org/downloads/)
* [jUnit](https://junit.org/junit5/)
* [Winium](https://github.com/2gis/Winium)
* [UISpy](https://github.com/2gis/Winium.Cruciatus/tree/master/tools/UISpy)

## Dependências:
* Selenium-java
* jUnit 4
* Commons-io
* Cucumber-junit
* Cucumber-java
* Jxl
* Poi-ooxml
* Winium
* UiSpy

## Instruções de execução:

###  - Plataforma
*Importante: 
O projeto foi criado para ser executado apenas no Windows.

Os metodos para criar o driver Winium estao na classe calculadoraWinCore.

Recomendado utilizar o Eclipse IDE, mas pode usar o Visual Studio Code ou Spring Tools Suite.

###  - Captura de elementos
*Descricao: Recomendo utilizar o UISpy, o mesmo esta na pasta drivers. ele e um aplicativo gratuito para capturar os elementos dos aplicativos do Windows.

###  - Fluxo
*Descricao: Este script ira executar uma compra online, seguindo o fluxo desde a escolha do produto ate a etapa de confirmacao da compra.

###  - Massas
*Descricao: Nao e necessario criar massas.

###  - Evidencias
*Descricao:

Apos a execucao as imagens de evidencias sao armazenadas na pasta screenshots.

Para visualizar as evidencias no documento pode usar o MS Office Word ou o LibreOffice

###  - Inicializar a automação
*Descricao:

Abrir uma das classes ***Test.java no Eclipse.

No caso para executar numa versao diferente do Windows 10, verifique se a calculadora esta no mesmo caminho da calculadora do Windows 10 (C:\\Windows\\System32\\calc.exe).