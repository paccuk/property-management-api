//package org.sandw.propertymanagementapi.modules.Properties.SeedWork;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.function.Executable;
//import org.sandw.propertymanagementapi.buildingblocks.Domain.BusinessRule;
//import org.sandw.propertymanagementapi.buildingblocks.Domain.BusinessRuleValidationException;
//import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEvent;
//import org.sandw.propertymanagementapi.buildingblocks.Domain.Entity;
//import org.sandw.propertymanagementapi.modules.Properties.Domain.SharedKernel.SystemClock;
//
//import java.util.List;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
//
//public class TestBase {
//
//    public static <T extends DomainEvent> T assertPublishedDomainEvent(Entity aggregate, Class<T> eventType) {
//        List<DomainEvent> domainEvents = DomainEventsTestHelper.getAllDomainEvents(aggregate);
//        return domainEvents.stream()
//                .filter(eventType::isInstance)
//                .map(eventType::cast)
//                .findFirst()
//                .orElseThrow(() -> new AssertionError(eventType.getSimpleName() + " event was not published"));
//    }
//
//    public static <T extends DomainEvent> void assertDomainEventNotPublished(Entity aggregate, Class<T> eventType) {
//        List<DomainEvent> domainEvents = DomainEventsTestHelper.getAllDomainEvents(aggregate);
//        boolean eventExists = domainEvents.stream().anyMatch(eventType::isInstance);
//        Assertions.assertFalse(eventExists);
//    }
//
//    public static <T extends DomainEvent> List<T> assertPublishedDomainEvents(Entity aggregate, Class<T> eventType) {
//        List<DomainEvent> domainEvents = DomainEventsTestHelper.getAllDomainEvents(aggregate);
//        List<T> filteredEvents = domainEvents.stream()
//                .filter(eventType::isInstance)
//                .map(eventType::cast)
//                .toList();
//
//        if (filteredEvents.isEmpty()) {
//            throw new AssertionError(eventType.getSimpleName() + " events were not published");
//        }
//
//        return filteredEvents;
//    }
//
//    public void assertBrokenRule(Class<? extends RuntimeException> ruleClass, Runnable testAction) {
//        assertThatThrownBy(testAction::run)
//                .isInstanceOf(BusinessRuleValidationException.class)
//                .hasCauseInstanceOf(ruleClass);
//    }
//
//    public static <TRule extends BusinessRule> void assertBrokenRule(Class<TRule> ruleClass, Executable executable) {
//        String message = "Expected " + ruleClass.getSimpleName() + " broken rule";
//
//        var businessRuleValidationException = Assertions.assertThrows(BusinessRuleValidationException.class, executable);
//        if (!ruleClass.isInstance(businessRuleValidationException.getBrokenRule())) {
//            throw new AssertionError(message + " but got " + businessRuleValidationException.getBrokenRule().getClass().getSimpleName());
//        }
//    }
//
//    @AfterEach
//    void afterEachTest() {
//        SystemClock.reset();
//    }
//}