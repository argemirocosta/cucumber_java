package steps;

import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class BuscaBookingSteps {

    private WebDriver driver;

    @Dado("que estou acessando o site do booking")
    public void queEstouAcessandoOSiteDoBooking() {
        System.setProperty("webdriver.chrome.driver", "/Users/argemirocosta/Documents/drivers - selenium/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://www.booking.com/");
    }

    @Dado("seleciono a cidade {string}")
    public void selecionoACidade(String cidade) {
        driver.findElement(By.id("ss")).sendKeys(cidade);

    }

    @Dado("seleciono a quantidade de adultos {int}")
    public void selecionoAQuantidadeDeAdultos(Integer quantidadeAdultos) {
        driver.findElement(By.xpath("//*[@id=\"xp__guests__toggle\"]/span[2]/span[1]")).click();
        if (quantidadeAdultos < 2) {
            driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/button[1]")).click();
        } else {
            for (int i = 2; i < quantidadeAdultos; i++) {
                driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[1]/div/div[2]/button[2]")).click();
            }
        }
    }

    @Dado("seleciono a quantidade de crianças {int}")
    public void selecionoAQuantidadeDeCrianças(Integer quantidadeCrianas) {
        driver.findElement(By.xpath("//*[@id=\"xp__guests__toggle\"]/span[2]/span[3]/span")).click();
        int quantidadeCriancasNaTela = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/span[1]")).getText());

        int total = (quantidadeCrianas - quantidadeCriancasNaTela);

        if(quantidadeCrianas == 0 && quantidadeCriancasNaTela == 0){
            return;
        }
        else if (quantidadeCrianas == 1 && quantidadeCriancasNaTela == 1){
            return;
        }
        else if (quantidadeCrianas == 1 && quantidadeCriancasNaTela == 0){
            driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/button[2]")).click();
        }
        else if (quantidadeCrianas == 0 && quantidadeCriancasNaTela == 1){
            driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/button[1]")).click();
        }
        else {
            for (int i = 0; i < total; i++) {
                driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[2]/div/div[2]/button[2]")).click();
            }
        }

    }

    @Dado("seleciono a quantidade de quartos {int}")
    public void selecionoAQuantidadeDeQuartos(Integer quantidadeQuartos) {
        driver.findElement(By.xpath("//*[@id=\"xp__guests__toggle\"]/span[2]/span[3]/span")).click();
        if (quantidadeQuartos < 2) {
            driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/div/div[2]/button[1]")).click();
        } else {
            for (int i = 1; i < quantidadeQuartos; i++) {
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
        String resultadosDaBusca = driver.findElement(By.xpath("//*[@id=\"breadcrumb\"]/ol/li[5]/a/div")).getText();
        assertEquals("Resultados de busca", resultadosDaBusca);
    }

    @After(value = "@booking")
    public void fecharBrowser() {
        driver.quit();
    }

}
