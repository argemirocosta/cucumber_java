package steps;

import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BuscaBookingSteps {

    private WebDriver driver;

    @Dado("que estou acessando o site do booking")
    public void queEstouAcessandoOSiteDoBooking() {
        System.setProperty("webdriver.chrome.driver", "/Users/argemirocosta/Documents/drivers - selenium/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.booking.com/");
    }

    @Dado("seleciono a cidade {string}")
    public void selecionoACidade(String string) {
        driver.findElement(By.id("ss")).sendKeys(string);

    }

    @Dado("seleciono a quantidade de adultos {int}")
    public void selecionoAQuantidadeDeAdultos(Integer int1) {
        driver.findElement(By.xpath("//*[@id=\"xp__guests__toggle\"]/span[2]/span[1]")).click();
        if (int1 < 2) {
            driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/button[1]")).click();
        } else {
            for (int i = 2; i < int1; i++) {
                driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/button[2]")).click();
            }
        }
    }

    @Dado("seleciono a quantidade de crianças {int}")
    public void selecionoAQuantidadeDeCrianças(Integer int1) {
        driver.findElement(By.xpath("//*[@id=\"xp__guests__toggle\"]/span[2]/span[2]/span")).click();

        for (int i = 0; i < int1; i++) {
            driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/button[2]")).click();
        }

    }

    @Dado("seleciono a quantidade de quartos {int}")
    public void selecionoAQuantidadeDeQuartos(Integer int1) {
        driver.findElement(By.xpath("//*[@id=\"xp__guests__toggle\"]/span[2]/span[3]/span")).click();
        if (int1 < 2) {
            driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/div/div[2]/button[1]")).click();
        } else {
            for (int i = 1; i < int1; i++) {
                driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/div/div[2]/button[2]")).click();
            }
        }
    }

    @Quando("clicar em Pesquisar")
    public void clicarEmPesquisar() {
        driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[4]/div[2]/button")).click();

    }

    @Então("vejo as acomodações encontradas")
    public void vejoAsAcomodaçõesEncontradas() {
        Assert.assertTrue(driver.getPageSource().contains("acomodações encontradas"));
    }

    @After
    public void fecharBrowser() {
        driver.quit();
    }

}
