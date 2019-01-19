package com.dlsc.workbenchfx.view;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.model.WorkbenchModule;
import com.dlsc.workbenchfx.view.controls.ToolbarItem;
import com.dlsc.workbenchfx.view.controls.selectionstrip.TabCell;
import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.scene.control.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Represents the presenter of the corresponding {@link ToolbarView}.
 *
 * @author François Martin
 * @author Marco Sanfratello
 */
public final class ToolbarPresenter extends Presenter {

  private static final Logger LOGGER =
      LogManager.getLogger(ToolbarPresenter.class.getName());
  private static final String STYLE_CLASS_ACTIVE_ADD_BUTTON = "active-add-button";

  private final Workbench model;
  private final ToolbarView view;

  // Strong reference to prevent garbage collection
  private final ObservableList<MenuItem> navigationDrawerItems;
  private final ObservableList<ToolbarItem> toolbarControlsLeft;
  private final ObservableList<ToolbarItem> toolbarControlsRight;
  private final ObservableList<WorkbenchModule> openModules;

  private static final PseudoClass EMPTY_STATE = PseudoClass.getPseudoClass("empty");

  /**
   * Creates a new {@link ToolbarPresenter} object for a corresponding {@link ToolbarView}.
   *
   * @param model the workbench, holding all data
   * @param view the corresponding {@link ToolbarView}
   */
  public ToolbarPresenter(Workbench model, ToolbarView view) {
    this.model = model;
    this.view = view;
    navigationDrawerItems = model.getNavigationDrawerItems();
    toolbarControlsLeft = model.getToolbarControlsLeft();
    toolbarControlsRight = model.getToolbarControlsRight();
    openModules = model.getOpenModules();
    init();
    // Adds initially a menuButton if necessary (size of items > 0)
    setupMenuBtn();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void initializeViewParts() {
    view.tabBar.setCellFactory(tab -> new TabCell());
    view.tabBar.getStylesheets().add(
        Workbench.class.getResource("css/selection-strip.css").toExternalForm()
    );

    // initially set the add module button as active
    view.addModuleBtn.getStyleClass().add(STYLE_CLASS_ACTIVE_ADD_BUTTON);

    view.addModuleBtn.requestFocus();
  }

  private void setupMenuBtn() {
    LOGGER.trace("setupMenuBtn() called");
    view.removeMenuBtn(); // Remove the menuBtn
    boolean empty = view.toolbarControl.isEmpty();
    view.topBox.pseudoClassStateChanged(EMPTY_STATE, empty); // Change the pseudoclass

    if (navigationDrawerItems.size() != 0) { // If setting it is required
      if (empty) { // If the toolbarControl is empty set it below
        LOGGER.trace("Put the button below into the bottomBox");
        view.addMenuBtnBottom();
      } else { // else put it into the topBox
        LOGGER.trace("Put it into the first position of toolbaritemsleft");
        view.addMenuBtnTop();
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setupEventHandlers() {
    // When the home button is clicked, the view changes
    view.addModuleBtn.setOnAction(event -> model.openAddModulePage());
    // When the menu button is clicked, the navigation drawer gets shown
    view.menuBtn.setOnAction(event -> model.showNavigationDrawer());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setupValueChangedListeners() {
    model.activeModuleProperty().addListener((observable, oldModule, newModule) -> {
      if (Objects.isNull(oldModule)) {
        // AddModuleView is the old value
        view.addModuleBtn.getStyleClass().remove(STYLE_CLASS_ACTIVE_ADD_BUTTON);
      }
      if (Objects.isNull(newModule)) {
        // AddModuleView is the new value
        view.addModuleBtn.getStyleClass().add(STYLE_CLASS_ACTIVE_ADD_BUTTON);
      }
    });

    // makes sure the menu button is only being displayed if there are navigation drawer items
    navigationDrawerItems.addListener((InvalidationListener) observable -> setupMenuBtn());
    // when the toolbarControl's emptyProperty changes, check the menuBtn's position
    view.toolbarControl.emptyProperty().addListener(
        (observable, wasEmpty, isEmpty) -> setupMenuBtn()); // Define where to put the menuBtn

    // handle changes to the active module in the tabs
    view.tabBar.selectedItemProperty().addListener((observable, oldModule, newModule) -> {
      if (!Objects.isNull(newModule)) {
        model.openModule(newModule);
      } else {
        model.openAddModulePage();
      }
    });
    model.activeModuleProperty().addListener((observable, oldModule, newModule) -> {
      view.tabBar.setSelectedItem(newModule);
    });
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void setupBindings() {
    // Binds content of the SelectionStrip to the Workbench content
    view.tabBar.itemsProperty().bindContent(openModules);

    // Bind items from toolbar to the ones of the workbench
    view.toolbarControl.toolbarControlsLeftProperty().bindContent(toolbarControlsLeft);
    view.toolbarControl.toolbarControlsRightProperty().bindContent(toolbarControlsRight);
  }
}
