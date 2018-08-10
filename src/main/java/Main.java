import org.parabot.environment.api.interfaces.Paintable;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;

import java.awt.*;
import java.util.ArrayList;

@ScriptManifest(author = "Atex", category = Category.THIEVING, description = "Ticket picker for dreamscape", name = "aPicker", servers = { "Dreamscape" }, version = 0.1)
public class Main extends Script {
    private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

    @Override
    public boolean onExecute() {
        strategies.add(new MainAction());
        provide(strategies);
        return true;
    }
}
