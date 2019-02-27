package com.jojo.application.db.repository;

import com.jojo.application.db.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public interface ParametersRepository extends JpaRepository<Parameter, Parameter.ParametersPk>
{
    List<Parameter> getParametersByParametersPk_ObjectId(Long objectId);

    default Map<Long, String> getParametersMapByObjectId(Long objectId)
    {
        return getParametersByParametersPk_ObjectId(objectId).stream().collect(Collectors.toMap(Parameter::getAttrId, Parameter::getValue));
    }

    default void updateParameters(Long objectId, Map<Long, String> parameters)
    {
        parameters.forEach((attrId, value) -> {
                               Parameter par = new Parameter(new Parameter.ParametersPk(attrId, objectId));
                               par.setValue(value);
                               this.save(par);
                           }
        );
    }
}
