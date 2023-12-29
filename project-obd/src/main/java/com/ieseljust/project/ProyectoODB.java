package com.ieseljust.proyectoodb;

import java.lang.reflect.Method;
import javax.persistence.*;
import java.sql.*;


/**
 *
 * @author Erik
 */

public class ProyectoODB {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vet_clinic", "alumne", "alumne");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("vet_clinic.odb");
            String[][] tablesAndClasses = {
                {"especies", "com.ieseljust.ModelER.Species"},
                {"propietari", "com.ieseljust.ModelER.Owners"},
                {"animals", "com.ieseljust.ModelER.Animals"},
                {"vets", "com.ieseljust.ModelER.Vets"},
                {"specializations", "com.ieseljust.ModelER.Specializations"},
                {"visits", "com.ieseljust.ModelER.Visits"}
            };

            // Loop through all the tables
            for (String[] tableAndClass : tablesAndClasses) {
                String table = tableAndClass[0];
                String className = tableAndClass[1];
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM " + table);

                while (rs.next()) {
                    Class<?> clazz = Class.forName(className);
                    Object entity = clazz.newInstance();

                    ResultSetMetaData rsmd = rs.getMetaData();
                    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                        String columnName = rsmd.getColumnName(i);
                        Object columnValue = rs.getObject(i);
                        String camelCaseName = toCamelCase(columnName);
                        String setterName = "set" + camelCaseName.substring(0, 1).toUpperCase() + camelCaseName.substring(1);
                        Method setter;

                        if (columnValue.getClass() == Integer.class || columnValue.getClass() == java.math.BigInteger.class) {
                            setter = clazz.getMethod(setterName, int.class);
                            setter.invoke(entity, ((Number) columnValue).intValue());
                        } else if (columnValue.getClass() == Boolean.class && clazz.getDeclaredField(camelCaseName).getType() == boolean.class) {
                            setter = clazz.getMethod(setterName, boolean.class);
                            setter.invoke(entity, ((Boolean) columnValue).booleanValue());
                        } else if (columnValue.getClass() == java.math.BigDecimal.class && clazz.getDeclaredField(camelCaseName).getType() == double.class) {
                            setter = clazz.getMethod(setterName, double.class);
                            setter.invoke(entity, ((java.math.BigDecimal) columnValue).doubleValue());
                        } else {
                            setter = clazz.getMethod(setterName, columnValue.getClass());
                            setter.invoke(entity, columnValue);
                        }
                    }

                    persistEntity(emf, entity);
                }
                rs.close();
                stmt.close();
            }

            conn.close();
            emf.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void persistEntity(EntityManagerFactory emf, Object entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    private static String toCamelCase(String s) {
        String[] parts = s.split("_");
        StringBuilder camelCaseString = new StringBuilder();

        for (int i = 0; i < parts.length; i++) {
            if (i == 0) {
                camelCaseString.append(parts[i]);
            } else {
                camelCaseString.append(parts[i].substring(0, 1).toUpperCase());
                camelCaseString.append(parts[i].substring(1).toLowerCase());
            }
        }

        return camelCaseString.toString();
    }
}
