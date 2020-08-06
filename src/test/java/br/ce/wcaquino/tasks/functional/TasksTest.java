package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
///import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\syune\\dev\\java\\seleniumDrivers\\chromedriver.exe");
		//WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		
		//WebDriver driver = new RemoteWebDriver(new URL("http://169.254.245.224:4444/wd/hub"), cap);
		//WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.185:4444/wd/hub"), cap);
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.120:4444/wd/hub"), cap);
		
		//driver.navigate().to("http://localhost:8001/tasks");
		///driver.navigate().to("http://192.168.0.185:8001/tasks");
		driver.navigate().to("http://192.168.0.120:8001/tasks");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {

		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\syune\\dev\\java\\seleniumDrivers\\chromedriver.exe");
		WebDriver driver = acessarAplicacao();
		
		try {
			//clicar em Add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descri��o
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		}finally {
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			//clicar em Add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descri��o
			//driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
			
		}finally {
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			//clicar em Add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descri��o
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever data
			//driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
			                     
		}finally {
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			//clicar em Add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descri��o
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		}finally {
			//fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void deveRemoverTarefaComSucesso() throws MalformedURLException {
		
		WebDriver driver = acessarAplicacao();
		
		try {
			//inserir uma tarefa
			
			//clicar em Add todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descri��o
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
			
			//remover a tarefa
			driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm']")).click();
			message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		}finally {
			//fechar o browser
			driver.quit();
		}
	}
	
	
}
