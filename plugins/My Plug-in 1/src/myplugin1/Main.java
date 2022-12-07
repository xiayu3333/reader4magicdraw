package myplugin1;

import com.nomagic.magicdraw.actions.ActionsConfiguratorsManager;
import com.nomagic.magicdraw.core.Application;
import com.nomagic.magicdraw.plugins.Plugin;

public class Main extends Plugin
{
	public static boolean initialized;
	
	@Override
	public void init()
	{
		initialized = true;
		Application.getInstance().getGUILog().showMessage("Text Reader initialized.");

		ReaderAction readerAction = new ReaderAction("XIAYU's text reader for MD", "Load New File");
		NextPageAction nextPageAction = new NextPageAction("next page", "Next Page");
		nextPageAction.setReaderAction(readerAction);
		PreviousPageAction previousPageAction = new PreviousPageAction("previous page", "Previous Page");

		MainMenuConfiguration configurator = new MainMenuConfiguration(readerAction, nextPageAction, previousPageAction);
		ActionsConfiguratorsManager.getInstance().addMainMenuConfigurator(configurator);

	}

	@Override
	public boolean close()
	{
		return true;
	}

	@Override
	public boolean isSupported()
	{
		return true;
	}
}
