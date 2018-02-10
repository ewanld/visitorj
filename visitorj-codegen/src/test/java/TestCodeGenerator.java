
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.visitorj.codegen.JavaClass;
import com.github.visitorj.codegen.VisitorGeneratorService;

public class TestCodeGenerator {

	public static void main(String[] args) throws Exception {
		final File outputDir = new File("E:\\workspaces\\perso\\fjdbc-insert-gui\\src-generated");
		final String packageName = "datagen.services";

		final List<JavaClass> classes = new ArrayList<>();
		classes.add(new JavaClass("datagen.services.methods.ConstantGenerator"));
		classes.add(new JavaClass("datagen.services.methods.RandomStringGenerator"));

		final VisitorGeneratorService codeGen = new VisitorGeneratorService();
		codeGen.generateAll("Generator", outputDir, classes, packageName);
	}
}
