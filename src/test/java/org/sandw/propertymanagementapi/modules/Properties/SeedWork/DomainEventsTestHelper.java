//package org.sandw.propertymanagementapi.modules.Properties.SeedWork;
//
//import org.sandw.propertymanagementapi.buildingblocks.Domain.DomainEvent;
//import org.sandw.propertymanagementapi.buildingblocks.Domain.Entity;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//
//public class DomainEventsTestHelper {
//
//    public static List<DomainEvent> getAllDomainEvents(Entity aggregate) {
//        List<DomainEvent> domainEvents = new ArrayList<>();
//
//        if (aggregate.getDomainEvents() != null) {
//            domainEvents.addAll(aggregate.getDomainEvents());
//        }
//
//        Field[] fields = getAllFields(aggregate.getClass());
//
//        for (Field field : fields) {
//            field.setAccessible(true);
//
//            try {
//                Object value = field.get(aggregate);
//
//                if (value instanceof Entity) {
//                    domainEvents.addAll(getAllDomainEvents((Entity) value));
//                }
//
//                if (value instanceof Collection<?>) {
//                    for (Object item : (Collection<?>) value) {
//                        if (item instanceof Entity) {
//                            domainEvents.addAll(getAllDomainEvents((Entity) item));
//                        }
//                    }
//                }
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException("Failed to access field: " + field.getName(), e);
//            }
//        }
//
//        return domainEvents;
//    }
//
//    public static void clearAllDomainEvents(Entity aggregate) {
//        aggregate.clearDomainEvents();
//
//        Field[] fields = getAllFields(aggregate.getClass());
//
//        for (Field field : fields) {
//            field.setAccessible(true);
//
//            try {
//                Object value = field.get(aggregate);
//
//                if (value instanceof Entity) {
//                    clearAllDomainEvents((Entity) value);
//                }
//
//                if (value instanceof Collection<?>) {
//                    for (Object item : (Collection<?>) value) {
//                        if (item instanceof Entity) {
//                            clearAllDomainEvents((Entity) item);
//                        }
//                    }
//                }
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException("Failed to access field: " + field.getName(), e);
//            }
//        }
//    }
//
//    private static Field[] getAllFields(Class<?> type) {
//        List<Field> fields = new ArrayList<>();
//        while (type != null) {
//            Collections.addAll(fields, type.getDeclaredFields());
//            type = type.getSuperclass();
//        }
//        return fields.toArray(new Field[0]);
//    }
//}
