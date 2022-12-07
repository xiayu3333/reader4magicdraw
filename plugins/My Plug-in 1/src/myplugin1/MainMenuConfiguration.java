package myplugin1;

import com.nomagic.actions.AMConfigurator;
import com.nomagic.actions.ActionsCategory;
import com.nomagic.actions.ActionsManager;
import com.nomagic.actions.NMAction;
import com.nomagic.magicdraw.actions.ActionsID;

import java.util.List;

public class MainMenuConfiguration implements AMConfigurator {
    private final ReaderAction action;
    private final NextPageAction nextPageAction;
    private final PreviousPageAction previousPageAction;


    public MainMenuConfiguration(ReaderAction action, NextPageAction nextPageAction, PreviousPageAction previousPageAction) {
        this.action = action;
        this.nextPageAction = nextPageAction;
        this.previousPageAction = previousPageAction;
    }

    @Override
    public void configure(ActionsManager actionsManager) {
        NMAction newProjectAction = actionsManager.getActionFor(ActionsID.NEW_PROJECT);
        if (newProjectAction != null) {
            ActionsCategory category = (ActionsCategory) actionsManager.getActionParent(newProjectAction);
            if (category != null){
                List<NMAction> actionsInCategory = category.getActions();

                ActionsCategory categoryParent = new ActionsCategory("parent_button", "Text Reader");
                categoryParent.setNested(true);
                categoryParent.addAction(action);
                categoryParent.addAction(nextPageAction);
                categoryParent.addAction(previousPageAction);

                actionsInCategory.add(categoryParent);
                category.setActions(actionsInCategory);
            }
        }
    }

}
