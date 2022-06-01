import amuse.interfaces.nodes.NodeException;
import amuse.interfaces.nodes.methods.AmuseTask;
import amuse.scheduler.tools.ToolInterface;
import tool.Teilproject2Main;

import java.io.File;
import java.io.IOException;

public class Main extends AmuseTask implements ToolInterface {
    public static void main(String[] args) throws IOException {
        Teilproject2Main.main(args);
    }

    @Override
    public void setParameters(String parameterString) throws NodeException {
        File tempFolder = new File(this.correspondingScheduler.getHomeFolder() + File.separator +
                "input" + File.separator + "task_" + this.correspondingScheduler.getTaskId());
        if(tempFolder.exists()) {
            File[] fileList = tempFolder.listFiles();
            for(File f :fileList) {
                f.delete();
            }
        } else {
            tempFolder.mkdirs();
        }
    }

    @Override
    public void initialize() throws NodeException {

    }

    @Override
    public void startTool() throws NodeException {

    }
}
