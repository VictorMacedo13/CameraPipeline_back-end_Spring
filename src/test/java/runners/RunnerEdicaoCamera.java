package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features",
		glue = {"steps", "config"},
		tags = "@EdicaoCamera",
		plugin = {"pretty", "html:target/retalhos/Edicao_Camera.html", "json:target/retalhos/report-Camera_Imagem.json"},
		monochrome = true, 
		snippets = SnippetType.CAMELCASE, 
		dryRun = false
		)
public class RunnerEdicaoCamera {

}
