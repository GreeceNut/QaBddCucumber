import io.cucumber.core.snippets.SnippetType;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "summary"},
        features = "src/test/resources/features",
        glue = {"Cucumber/steps"},
        tags = "@all",
        dryRun = false
)
public class RunCucumberTest {

}
