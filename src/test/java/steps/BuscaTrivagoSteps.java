package steps;

import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.assertEquals;

public class BuscaTrivagoSteps {

    private WebDriver driver;

    @Dado("que estou acessando o site do trivago")
    public void queEstouAcessandoOSiteDoTrivago() {
        System.setProperty("webdriver.chrome.driver", "/Users/argemirocosta/Documents/drivers - selenium/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.trivago.com.br/");
    }

    @Dado("escolho a cidade {string}")
    public void escolhoACidade(String string) {
        driver.findElement(By.id("querytext")).sendKeys(string);
    }

    @Dado("seleciono o tipo de quarto {string}")
    public void selecionoOTipoDeQuarto(String string) {

        driver.findElement(By.xpath("//*[@id=\"js-fullscreen-hero\"]/div[1]/form/button[1]/span")).click();

        if (string.equals("duplo")) {
            driver.findElement(By.xpath("//*[@id=\"js-fullscreen-hero\"]/div[1]/form/div[4]/div/div/ul/li[2]/button/div")).click();
        } else if (string.equals("individual")) {
            driver.findElement(By.xpath("//*[@id=\"js-fullscreen-hero\"]/div[1]/form/div[4]/div/div/ul/li[1]/button/div")).click();
        }
    }

    @Quando("clicar no botão Pesquisar")
    public void clicarNoBotãoPesquisar() {
        driver.findElement(By.xpath("//*[@id=\"js-fullscreen-hero\"]/div[1]/form/button[2]")).click();
    }

    @Quando("seleciono para ordenar por distância")
    public void selecionoParaOrdenarPorDistância() {
        WebElement comboOrdenacaoElement = driver.findElement(By.id("mf-select-sortby"));
        Select comboOrdenacaoSelect = new Select(comboOrdenacaoElement);
        comboOrdenacaoSelect.selectByValue("3");
    }

    @Então("visualizo as acomodações encontradas")
    public void visualizoAsAcomodaçõesEncontradas() {
        String textoMaisFiltros = driver.findElement(By.xpath("//*[@id=\"page_wrapper\"]/section/div/div/ul/li[5]/button/strong")).getText();
        assertEquals("Mais filtros ", textoMaisFiltros);
    }

    @After(value = "@trivago")
    public void fecharBrowser() {
        driver.quit();
    }

}
