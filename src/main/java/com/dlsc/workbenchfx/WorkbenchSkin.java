package com.dlsc.workbenchfx;

import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.dlsc.workbenchfx.view.*;
import com.dlsc.workbenchfx.view.controls.selectionstrip.SelectionStrip;
import javafx.scene.control.SkinBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents the Skin which is made for the {@link Workbench}.
 * It creates all the Views and Presenters which are needed and sets the stylesheets.
 *
 * @author François Martin
 * @author Marco Sanfratello
 */
public final class WorkbenchSkin extends SkinBase<Workbench> {

  private static final Logger LOGGER =
      LogManager.getLogger(WorkbenchSkin.class.getName());

  // Views
  private SelectionStrip<WorkbenchModule> tabBar;

  private ToolbarView toolbarView;
  private ToolbarPresenter toolbarPresenter;

  private AddModuleView addModuleView;
  private AddModulePresenter addModulePresenter;

  private ContentView contentView;
  private ContentPresenter contentPresenter;

  private WorkbenchView workbenchView;
  private WorkbenchPresenter workbenchPresenter;

  /**
   * Creates a skin for a given {@link Workbench}.
   * Contains all views and presenters and sets also the default stylesheet.
   *
   * @param workbench for which this skin is created
   */
  public WorkbenchSkin(Workbench workbench) {
    super(workbench);

    initViews(workbench);

    getChildren().add(workbenchView);
  }

  private void initViews(Workbench model) {
    toolbarView = new ToolbarView();
    toolbarPresenter = new ToolbarPresenter(model, toolbarView);

    addModuleView = new AddModuleView();
    addModulePresenter = new AddModulePresenter(model, addModuleView);

    contentView = new ContentView(addModuleView);
    contentPresenter = new ContentPresenter(model, contentView);

    workbenchView = new WorkbenchView(toolbarView, addModuleView, contentView);
    workbenchPresenter = new WorkbenchPresenter(model, workbenchView);
  }

}
