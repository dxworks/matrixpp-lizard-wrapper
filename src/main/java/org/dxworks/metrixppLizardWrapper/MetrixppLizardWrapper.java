package org.dxworks.metrixppLizardWrapper;

import com.google.gson.Gson;
import org.dxworks.metrixppLizardWrapper.Entity.MetaInfo;
import org.dxworks.metrixppLizardWrapper.Entity.OutputWithMetaInfo;
import org.dxworks.metrixppLizardWrapper.Entity.UnifiedOutput;

import java.util.LinkedList;
import java.util.List;

public class MetrixppLizardWrapper {
    public static void main(String[] args) {

        MetaInfo metaInfo = new MetaInfo();

        OutputWithMetaInfo finalOutput = new OutputWithMetaInfo();

        finalOutput.setMetaInfo(metaInfo);

        long beginTime = System.currentTimeMillis();

        metaInfo.setTimestamp(beginTime);

        metaInfo.setLizard_status("SUCCESS");

        metaInfo.setMetrixpp_status("SUCCESS");

        List<UnifiedOutput> results = new LinkedList<>();

        long endTime = System.currentTimeMillis();

        long duration = beginTime - endTime;

        metaInfo.setDuration(duration);

        finalOutput.setResults(results);

        Gson gson = new Gson();

        System.out.println(gson.toJson(finalOutput));
    }
}
