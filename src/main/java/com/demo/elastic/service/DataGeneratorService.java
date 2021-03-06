package com.demo.elastic.service;

import com.demo.elastic.config.DataGenerationConfig;
import com.demo.elastic.helper.ResLoaderHelper;
import com.demo.elastic.model.area.Address;
import com.demo.elastic.model.element.BaseElement;
import com.demo.elastic.model.element.EducationElement;
import com.demo.elastic.model.element.ElementStateEnum;
import com.demo.elastic.model.element.ElementTag;
import com.demo.elastic.model.element.InteractiveElement;
import com.demo.elastic.model.element.ProgrammeElement;
import com.demo.elastic.model.org.Org;
import com.demo.elastic.model.person.Person;
import com.demo.elastic.repository.BaseElementRepository;
import com.demo.elastic.repository.ElementTagRepository;
import com.demo.elastic.repository.OrgRepository;
import com.demo.elastic.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
@AllArgsConstructor
public class DataGeneratorService {

    private BaseElementRepository baseElementRepository;

    private PersonRepository personRepository;

    private OrgRepository orgRepository;

    private ElementTagRepository elementTagRepository;

    private ResLoaderHelper resLoaderHelper;

    private DataGenerationConfig dataGenerationConfig;

    private List<String> dictionaryNames = new ArrayList<>();

    private List<Pair<LocalDate, LocalDate>> rangeDates = new ArrayList<>();

    public void generateTestData() {
        long n = dataGenerationConfig.getEntityCount();

        List<Person> people = generatePeople();
        Org org = generateOrg();
        Pair<LocalDate, LocalDate> range = generateRangeDate();
        List<ElementTag> tags = generateTags();

        StopWatch benchmark = new StopWatch();

        benchmark.start("Create " + n + " elements");

        for (long i = 0; i < n; i++) {

            BaseElement element = createNextElement(i);

            String name = generateName();
            Random random = new Random();

            element.setName(name);
            element.setBeginDate(range.getFirst());
            element.setEndDate(range.getSecond());
            element.setZet((double) random.nextInt(150));
            element.setState(ElementStateEnum.getRandomState());

            List<ElementTag> needSaveTags = new ArrayList<>();

            if (Math.random() < 0.2) {
                int count = random.nextInt(tags.size());

                needSaveTags = tags.subList(0, count);

                element.setElementTags(needSaveTags);
            }

            if (element instanceof EducationElement) {
                ((EducationElement) element).setOutbound(random.nextInt(2) > 0);
            } else if (element instanceof InteractiveElement) {
                int indexPerson = random.nextInt(people.size());

                ((InteractiveElement) element).setReviewer(people.get(indexPerson));
            } else {
                ((ProgrammeElement) element).setCost((long) random.nextInt(1000000));
                ((ProgrammeElement) element).setGovCost((long) random.nextInt(100000));
                ((ProgrammeElement) element).setOrg(org);
            }

            BaseElement saveElement = baseElementRepository.save(element);

            for (ElementTag tag : needSaveTags) {
                List<BaseElement> elements = tag.getBaseElements();

                elements.add(saveElement);

                elementTagRepository.save(tag);
            }
        }

        benchmark.stop();

        log.info(benchmark.prettyPrint());
    }

    private List<ElementTag> generateTags() {
        List<String> names = Arrays.asList("Рекомендовано РАМ", "Рекомендовано Минздрав", "EU");

        List<ElementTag> tags = new ArrayList<>(5);

        for (String name : names) {
            ElementTag tag = new ElementTag();

            tag.setName(name);

            ElementTag saveTag = elementTagRepository.save(tag);
            tags.add(saveTag);
        }

        return tags;
    }

    private Org generateOrg() {
        Org org = new Org();
        Address address = new Address();

        address.setCode("region:Samara");

        org.setLevel(10);
        org.setName("Org");
        org.setRegisterDate(LocalDate.now());
        org.setAddress(address);

        return orgRepository.save(org);
    }

    private List<Person> generatePeople() {
        List<String> firstNames = Arrays.asList("Adam", "Joe", "Carol");
        List<String> lastNames = Arrays.asList("Smith", "Brook", "Mozby");
        List<String> codes = Arrays.asList("region:Samara", "region:Moscow", "region:Pensa");

        List<Person> people = new ArrayList<>(5);

        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            Address address = new Address();

            address.setCode(codes.get(i));

            person.setFirstName(firstNames.get(i));
            person.setLastName(lastNames.get(i));
            person.setDateOfBirth(LocalDate.now());
            person.setAddress(address);

            Person savePerson = personRepository.save(person);
            people.add(savePerson);
        }

        return people;
    }

    private BaseElement createNextElement(long n) {
        int dis = (int) (n % 3);

        switch (dis) {
            case 0:
                return new EducationElement();
            case 1:
                return new InteractiveElement();
            case 2:
                return new ProgrammeElement();
        }

        throw new RuntimeException("mod is not correct");
    }

    private String generateName() {
        if (dictionaryNames.isEmpty()) {
            dictionaryNames = resLoaderHelper
                    .loadResFileContentAsList(dataGenerationConfig.getResPathNameDictionary());
        }

        int index = new Random().nextInt(dictionaryNames.size());

        return dictionaryNames.get(index);
    }

    private Pair<LocalDate, LocalDate> generateRangeDate() {
        if (rangeDates.isEmpty()) {
            rangeDates = createRange();
        }

        int index = new Random().nextInt(rangeDates.size());

        return rangeDates.get(index);
    }

    private List<Pair<LocalDate, LocalDate>> createRange() {
        List<Pair<LocalDate, LocalDate>> rangeDates = new ArrayList<>();
        LocalDate now = LocalDate.now();

        int currentYear = now.getYear();
        int hafMon = 6;

        for (int i = 0; i < dataGenerationConfig.getRangeDate(); i++) {
            Random random = new Random();

            int beginMon = random.nextInt(hafMon) + 1;
            int endMon = random.nextInt(beginMon) + hafMon;

            LocalDate beginDate = LocalDate.of(currentYear, beginMon, 1);
            int beginDays = random.nextInt(beginDate.lengthOfMonth());
            beginDate = beginDate.plusDays(beginDays);

            LocalDate endDate = LocalDate.of(currentYear, endMon, 1);
            int endDays = random.nextInt(endDate.lengthOfMonth());
            endDate = endDate.plusDays(endDays);

            rangeDates.add(Pair.of(beginDate, endDate));
        }

        return rangeDates;
    }
}
