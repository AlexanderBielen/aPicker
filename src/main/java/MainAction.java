import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.SceneObject;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.rev317.min.api.methods.Game.isLoggedIn;

public class MainAction implements Strategy {
    private final int CLICK_INTERVAL = 2306;

    private final int[] STALL_IDS = {4874, 4875, 4876, 4877, 4878};

    @Override
    public boolean activate() {
        return isLoggedIn() || relog();
    }

    @Override
    public void execute() {
        int stall_id;
        int thievingLevel = Skill.THIEVING.getLevel();
        
        try {
            if(thievingLevel < 50) {
                stall_id = STALL_IDS[0];
            } else if(thievingLevel < 70) {
                stall_id = STALL_IDS[1];
            } else if(thievingLevel < 85) {
                stall_id = STALL_IDS[2];
            } else if(thievingLevel < 99) {
                stall_id = STALL_IDS[3];
            } else {
                stall_id = STALL_IDS[4];
            }

            SceneObject stall = SceneObjects.getClosest(stall_id);
            if(stall == null) {
                Keyboard.getInstance().sendKeys("::home");
                System.out.println("No thieving stall found");
                Time.sleep(5000);
            } else {
                stall.interact(SceneObjects.Option.USE);
            }
            
            Time.sleep(CLICK_INTERVAL);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean relog() {
        for(int i=0; i<3; i++) { // Try to relog 3 times
            Time.sleep(10000);
            Keyboard.getInstance().clickKey(KeyEvent.VK_ENTER);
            Time.sleep(5000);
            return isLoggedIn();
        }
        
        return false;
    }
}
