package com.demo.elastic.helper;

import com.demo.elastic.model.element.BaseElement;
import com.demo.elastic.model.element.EducationElement;
import com.demo.elastic.model.element.InteractiveElement;
import com.demo.elastic.model.element.ProgrammeElement;
import com.demo.elastic.search.model.ElasticDocument;
import org.springframework.stereotype.Component;

@Component("demo_ElementHelper")
public class ElementHelper {

    public ElasticDocument convertToDocument(BaseElement element) {
        ElasticDocument document = new ElasticDocument();

        document.setId(element.getId());
        document.setName(element.getName());
        document.setBeginDate(element.getBeginDate());
        document.setEndDate(element.getEndDate());
        document.setStatus(element.getState().getState());
        document.setZet(element.getZet());

        if (element instanceof ProgrammeElement) {
            document.setCost(((ProgrammeElement) element).getCost());
            document.setGovCost(((ProgrammeElement) element).getGovCost());
            document.setOrgId(((ProgrammeElement) element).getOrg().getId());
        } else if (element instanceof InteractiveElement) {
            document.setReviewerId(((InteractiveElement) element).getReviewer().getId());
        } else {
            document.setOutbound(((EducationElement) element).getOutbound());
        }

        return document;
    }
}
