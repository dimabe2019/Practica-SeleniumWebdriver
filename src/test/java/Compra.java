import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

import static org.junit.Assert.assertEquals;

public class Compra {

    public static WebDriver firefoxDriver;

    @Before
    public void BuscarPagina() {
        FirefoxOptions options = new FirefoxOptions();

        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-infobars");

        firefoxDriver = new FirefoxDriver(options);
        firefoxDriver.get("http://automationpractice.com/index.php?");
    }

    @Test
    public void RealizarProcesoDePago() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Boton de sign in
        WebElement botonSignIn = firefoxDriver.findElement(By.linkText("Sign in"));
        botonSignIn.click();
        //firefoxDriver.findElement(By.linkText("Sign in")).click();

        //input de email
        WebElement inputEmail = firefoxDriver.findElement(By.xpath("//input[@id='email']"));
        inputEmail.sendKeys("diego72034@gmail.com");
        //firefoxDriver.findElement(By.xpath("//input[@id='email']")).sendKeys("diego72034@gmail.com");

        //input de contraseña
        WebElement inputContrasena = firefoxDriver.findElement(By.xpath("//input[@id='passwd']"));
        inputContrasena.sendKeys("ing_diego");
        //firefoxDriver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("ing_diego");

        //Boton de sing in
        WebElement btnSing = firefoxDriver.findElement(By.xpath("//button[@id='SubmitLogin']"));
        btnSing.click();
        //firefoxDriver.findElement(By.xpath("//button[@id='SubmitLogin']")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //boton de la pestaña T-shirts
        WebElement pestanaTshirts = firefoxDriver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div[3]/div/div/div[6]/ul/li[3]/a"));
        pestanaTshirts.click();
        //firefoxDriver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div[3]/div/div/div[6]/ul/li[3]/a")).click();
        //firefoxDriver.findElement(By.xpath("//a[@title='T-shirts']//ancestor::li[@class='']")).click();
        //WebElement botonCategoria = firefoxDriver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?id_category=5&controller=category']//ancestor::li[@class='']"));

        //botonCategoria.click();


        //Creando el accion
        Actions accion = new Actions(firefoxDriver);

        //Encontrar la imagen del producto
        WebElement imagenCompra = firefoxDriver.findElement(By.xpath("//img[@title='Faded Short Sleeve T-shirts']"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JavascriptExecutor jse = (JavascriptExecutor)firefoxDriver;
        jse.executeScript("window.scrollBy(0,550)");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //
        accion.moveToElement(imagenCompra).moveToElement(firefoxDriver.findElement(By.xpath("//span[contains(text(),'Add to cart')]"))).click().build().perform();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //presionar clic del boton proceed check
        WebElement botonCompra1 = firefoxDriver.findElement(By.xpath("//span[contains(text(),'Proceed to checkout')]"));
        botonCompra1.click();
        
        WebElement botonCompra2 = firefoxDriver.findElement(By.xpath("//p//*[contains(text(),'Proceed to checkout')]"));
        botonCompra2.click();

        WebElement botonCompra3 = firefoxDriver.findElement(By.xpath("//button[@name='processAddress']"));
        botonCompra3.click();

        WebElement checkServ = firefoxDriver.findElement(By.xpath("//input[@id='cgv']"));
        checkServ.click();

        WebElement botonCompra4 = firefoxDriver.findElement(By.xpath("//button[@name='processCarrier']"));
        botonCompra4.click();

        WebElement botonCompra5 = firefoxDriver.findElement(By.xpath("//a[@class='cheque']"));
        botonCompra5.click();

        WebElement botonConfirm = firefoxDriver.findElement(By.xpath("//span[contains(text(),'I confirm my order')]"));
        botonConfirm.click();


    }

    @After
    public void ValidarElPago() {
        WebElement mensajeExitoso = firefoxDriver.findElement(By.xpath("//p[contains(text(),'Your order')]"));

        assertEquals(mensajeExitoso.getText(),"Your order on My Store is complete.");

        //Tambien se puede hacer de la siguiente manera
        //Assert.assertTrue(mensajeExitoso.isDisplayed()); esta funcion dice que si se muestra el menaje o no

        firefoxDriver.quit();
    }
}
