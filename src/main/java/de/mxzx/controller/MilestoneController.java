package de.mxzx.controller;

import de.mxzx.model.Clan;
import de.mxzx.model.Milestone;
import de.mxzx.service.ClanService;
import de.mxzx.service.MilestoneService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class MilestoneController {

    private final Map<String, Milestone> cache;
    private final MilestoneService service;

    public Milestone getData(String id) {
        Milestone data;
        if (!getCache().containsKey(id)) {
            data = getService().load(id);
            getCache().put(data.getName(), data);
        }
        return getCache().get(id);
    }

    public void saveData(Milestone data) {
        getService().save(data);
        getCache().put(data.getName(), data);
    }

}
