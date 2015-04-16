package com.canoo.dolphin.workflow.server.model;

import com.canoo.dolphin.collections.ObservableList;
import com.canoo.dolphin.mapping.DolphinBean;
import com.canoo.dolphin.mapping.Property;

@DolphinBean("ProcessDefinition")
public class ProcessDefinition {

    private Property<String> id;
    private Property<String> name;
    private ObservableList<BaseProcessInstance> processInstances;

    public ObservableList<BaseProcessInstance> getProcessInstances() {
        return processInstances;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public String getId() {
        return id.get();
    }

    public void setId(String value) {
        id.set(value);
    }
}
