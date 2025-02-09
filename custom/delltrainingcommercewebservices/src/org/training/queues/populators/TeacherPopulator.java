package org.training.queues.populators;

import org.training.model.TeacherModel;

public class TeacherPopulator implements Populator<TeacherModel, TeacherData> {
    @Override
    public void populate(TeacherModel source, TeacherData target) {
        target.setId(source.getId());
        target.setName(source.getName());
    }
}
