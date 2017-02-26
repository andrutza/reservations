package mfcc.reservations.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(using = ModelDeserializer.class)
public abstract class Model {

    private Integer id;
    private ModelType modelType;
    private Map<String,Object> dataForUpdate = new HashMap<String,Object>();

    public Model(ModelType modelType) {
        this.modelType = modelType;
    }

    public Model(Integer id, ModelType modelType) {
        this.id = id;
        this.modelType = modelType;
    }

    public Integer getId() {
        return id;
    }

    public Map<String, Object> getDataForUpdate() {
        return dataForUpdate;
    }

    public void setDataForUpdate(Map<String, Object> dataForUpdate) {
        this.dataForUpdate = dataForUpdate;
    }

    public ModelType getModelType() {
        return modelType;
    }

    @Override
    public String toString() {
        return "Model{" +
                "modelType=" + modelType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        return modelType == model.modelType;

    }

    @Override
    public int hashCode() {
        return modelType != null ? modelType.hashCode() : 0;
    }
}
