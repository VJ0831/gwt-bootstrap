/*
 *  Copyright 2012 GWT-Bootstrap
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.github.gwtbootstrap.showcase.client.components;

import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.Section;
import com.github.gwtbootstrap.showcase.client.Alerts;
import com.github.gwtbootstrap.showcase.client.ClientFactory;
import com.github.gwtbootstrap.showcase.client.Datepicker;
import com.github.gwtbootstrap.showcase.client.Dropdown;
import com.github.gwtbootstrap.showcase.client.Modal;
import com.github.gwtbootstrap.showcase.client.Navigation;
import com.github.gwtbootstrap.showcase.client.Pagination;
import com.github.gwtbootstrap.showcase.client.Popovers;
import com.github.gwtbootstrap.showcase.client.Subnav;
import com.github.gwtbootstrap.showcase.client.Tooltips;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class ComponentView extends Composite {

    private static ComponentViewUiBinder uiBinder = GWT
            .create(ComponentViewUiBinder.class);

    @UiField
    HTMLPanel container;

    private final ClientFactory clientFactory;
    
    interface ComponentViewUiBinder extends UiBinder<Widget, ComponentView> {
    }

    public ComponentView(ClientFactory clientFactory) {
        this.clientFactory = clientFactory;
        
        
        initWidget(uiBinder.createAndBindUi(this));
        clientFactory.getSubnav().clear();
        
        addWidget("Dropdown", "dropdown", new Dropdown());
        addWidget("Navigation", "navigation", new Navigation());
        addWidget("Pagination", "pagination", new Pagination());
        addWidget("Alert", "alert", new Alerts());
        addWidget("Tooltips", "tooltip", new Tooltips());
        addWidget("Popovers", "popovers", new Popovers());
        addWidget("Datepicker", "datepicker", new Datepicker());
        addWidget("Modal", "modal", new Modal());
        
        clientFactory.getSubnav().getSpy().configure();
    }
    
    protected void addWidget(String header , String target, Widget widget) {
        Subnav subnav = clientFactory.getSubnav();

        PlaceHistoryMapper mapper = clientFactory.getPlaceHistoryMapper();
        
        String newTarget = mapper.getToken(clientFactory.getPlaceController().getWhere()).split(":")[0] + ":"  + target;
        
        Section section = new Section(newTarget);
        
        section.add(widget);
        
        container.add(section);
        
        subnav.add(new NavLink(header , "#" + newTarget));
        
    }


}
