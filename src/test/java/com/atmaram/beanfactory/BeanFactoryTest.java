package com.atmaram.beanfactory;

import com.atmaram.beanfactory.generators.*;
import com.atmaram.beanfactory.generators.custom.NameGenerator;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
public class BeanFactoryTest {

    public static class Class1
    {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    @Test
    public void should_create_bean_without_generators() throws IllegalAccessException, InvocationTargetException {
        BeanFactory beanFactory=new BeanFactory();
        Class1 class1=beanFactory.create(Class1.class);
        assertThat(class1).isNotNull();
    }
    public static class ClassWithList{
        List<String> names;

        public List<String> getNames() {
            return names;
        }

        public void setNames(List<String> names) {
            this.names = names;
        }
    }
    @Test
    public void should_create_list_without_generators() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        ClassWithList classWithList=beanFactory.create(ClassWithList.class);
        assertThat(classWithList).isNotNull();
        assertThat(classWithList.getNames()).isNotNull();
        assertThat(classWithList.getNames().size()).isBetween(0,10);
        if(classWithList.getNames().size()>0){
            assertThat(classWithList.getNames().get(0)).isInstanceOf(String.class);
        }
    }
    @Test
    public void should_create_list_with_listgenerator_and_without_memeber_generator() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        beanFactory.apply(new ListGenerator<String>(2,5)).on(ClassWithList.class).getNames();
        ClassWithList classWithList=beanFactory.create(ClassWithList.class);
        assertThat(classWithList).isNotNull();
        assertThat(classWithList.getNames()).isNotNull();
        assertThat(classWithList.getNames().size()).isBetween(2,5);
        if(classWithList.getNames().size()>0){
            assertThat(classWithList.getNames().get(0)).isInstanceOf(String.class);
        }
    }
    @Test
    public void should_create_list_with_listgenerator_and_with_memeber_generator() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        beanFactory.apply(new ListGenerator<String>(3, 6, (Generator) () -> "Hello")).on(ClassWithList.class).getNames();
        ClassWithList classWithList=beanFactory.create(ClassWithList.class);
        assertThat(classWithList).isNotNull();
        assertThat(classWithList.getNames()).isNotNull();
        assertThat(classWithList.getNames().size()).isBetween(3,6);
        if(classWithList.getNames().size()>0){
            assertThat(classWithList.getNames().get(0)).isInstanceOf(String.class);
            assertThat(classWithList.getNames().get(0)).isEqualTo("Hello");
        }
    }

    public static class ClassWithSet{
        Set<String> names;

        public Set<String> getNames() {
            return names;
        }

        public void setNames(Set<String> names) {
            this.names = names;
        }
    }
    @Test
    public void should_create_set_without_generators() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        ClassWithSet classWithSet=beanFactory.create(ClassWithSet.class);
        assertThat(classWithSet).isNotNull();
        assertThat(classWithSet.getNames()).isNotNull();
        assertThat(classWithSet.getNames().size()).isBetween(0,10);
        if(classWithSet.getNames().size()>0){
            assertThat(classWithSet.getNames().iterator().next()).isInstanceOf(String.class);
        }
    }
    @Test
    public void should_create_set_with_setgenerator_and_without_memeber_generator() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        beanFactory.apply(new SetGenerator(2,5)).on(ClassWithSet.class).getNames();
        ClassWithSet classWithSet=beanFactory.create(ClassWithSet.class);
        assertThat(classWithSet).isNotNull();
        assertThat(classWithSet.getNames()).isNotNull();
        assertThat(classWithSet.getNames().size()).isBetween(2,5);
        if(classWithSet.getNames().size()>0){
            assertThat(classWithSet.getNames().iterator().next()).isInstanceOf(String.class);
        }
    }
    @Test
    public void should_create_set_with_setgenerator_and_with_memeber_generator() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        beanFactory.apply(new SetGenerator(3, 6, new StringGenerator())).on(ClassWithSet.class).getNames();
        ClassWithSet classWithSet=beanFactory.create(ClassWithSet.class);
        assertThat(classWithSet).isNotNull();
        assertThat(classWithSet.getNames()).isNotNull();
        assertThat(classWithSet.getNames().size()).isBetween(3,6);
        if(classWithSet.getNames().size()>0){
            assertThat(classWithSet.getNames().iterator().next()).isInstanceOf(String.class);
        }
    }

    public static class ClassWithMap{
        Map<Integer,String> pointers;

        public Map<Integer, String> getPointers() {
            return pointers;
        }

        public void setPointers(Map<Integer, String> pointers) {
            this.pointers = pointers;
        }
    }

    @Test
    public void should_create_map_without_generators() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        ClassWithMap classWithMap=beanFactory.create(ClassWithMap.class);
        assertThat(classWithMap).isNotNull();
        assertThat(classWithMap.getPointers()).isNotNull();
        assertThat(classWithMap.getPointers().size()).isBetween(0,10);
        assertMap(classWithMap);
    }
    @Test
    public void should_create_mapn_with_mapgenerator_and_without_key_and_value_generator() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        beanFactory.apply(new MapGenerator(2,5)).on(ClassWithMap.class).getPointers();
        ClassWithMap classWithMap=beanFactory.create(ClassWithMap.class);
        assertThat(classWithMap).isNotNull();
        assertThat(classWithMap.getPointers()).isNotNull();
        assertThat(classWithMap.getPointers().size()).isBetween(2,5);
        assertMap(classWithMap);
    }

    private void assertMap(ClassWithMap classWithMap) {
        if(classWithMap.getPointers().size()>0){
            for (Object key:
                    classWithMap.getPointers().keySet()) {
                assertThat(key).isNotNull();
                assertThat(key).isInstanceOf(Integer.class);
                Object value=classWithMap.getPointers().get(key);
                assertThat(value).isNotNull();
                assertThat(value).isInstanceOf(String.class);
            }
        }
    }

    @Test
    public void should_create_map_with_mapgenerator_with_key_generator_and_without_value_generator() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        MapGenerator mapGenerator=new MapGenerator(3,6);
        mapGenerator.setKeyGenerator(new IntegerGenerator(100,100000));
        beanFactory.apply(mapGenerator).on(ClassWithMap.class).getPointers();
        ClassWithMap classWithMap=beanFactory.create(ClassWithMap.class);
        assertThat(classWithMap).isNotNull();
        assertThat(classWithMap.getPointers()).isNotNull();
        assertThat(classWithMap.getPointers().size()).isBetween(3,6);
        if(classWithMap.getPointers().size()>0){
            for (Object key:
                    classWithMap.getPointers().keySet()) {
                assertThat(key).isNotNull();
                assertThat(key).isInstanceOf(Integer.class);
                assertThat((Integer) key).isBetween(100,100000);
                Object value=classWithMap.getPointers().get(key);
                assertThat(value).isNotNull();
                assertThat(value).isInstanceOf(String.class);
            }
        }
    }

    @Test
    public void should_create_map_with_mapgenerator_with_value_generator_and_without_key_generator() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        MapGenerator mapGenerator=new MapGenerator(3,6);
        mapGenerator.setValueGenerator((Generator<String>) () -> "Hello");
        beanFactory.apply(mapGenerator).on(ClassWithMap.class).getPointers();
        ClassWithMap classWithMap=beanFactory.create(ClassWithMap.class);
        assertThat(classWithMap).isNotNull();
        assertThat(classWithMap.getPointers()).isNotNull();
        assertThat(classWithMap.getPointers().size()).isBetween(3,6);
        if(classWithMap.getPointers().size()>0){
            for (Object key:
                    classWithMap.getPointers().keySet()) {
                assertThat(key).isNotNull();
                assertThat(key).isInstanceOf(Integer.class);
                assertThat((Integer) key).isBetween(0,10000);
                Object value=classWithMap.getPointers().get(key);
                assertThat(value).isNotNull();
                assertThat(value).isInstanceOf(String.class);
                assertThat(value).isEqualTo("Hello");
            }
        }
    }

    @Test
    public void should_create_map_with_mapgenerator_with_key_generator_and_with_value_generator() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        MapGenerator mapGenerator=new MapGenerator(3,6);
        mapGenerator.setKeyGenerator(new IntegerGenerator(100,100000));
        mapGenerator.setValueGenerator((Generator<String>) () -> "Hello");
        beanFactory.apply(mapGenerator).on(ClassWithMap.class).getPointers();
        ClassWithMap classWithMap=beanFactory.create(ClassWithMap.class);
        assertThat(classWithMap).isNotNull();
        assertThat(classWithMap.getPointers()).isNotNull();
        assertThat(classWithMap.getPointers().size()).isBetween(3,6);
        if(classWithMap.getPointers().size()>0){
            for (Object key:
                    classWithMap.getPointers().keySet()) {
                assertThat(key).isNotNull();
                assertThat(key).isInstanceOf(Integer.class);
                assertThat((Integer) key).isBetween(100,100000);
                Object value=classWithMap.getPointers().get(key);
                assertThat(value).isNotNull();
                assertThat(value).isInstanceOf(String.class);
                assertThat(value).isEqualTo("Hello");
            }
        }
    }

    public static class ClassWithDefaults{
        String name;
        int age;
        long id;
        float weight;
        double bmi;
        boolean isPositive;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public float getWeight() {
            return weight;
        }

        public void setWeight(float weight) {
            this.weight = weight;
        }

        public double getBmi() {
            return bmi;
        }

        public void setBmi(double bmi) {
            this.bmi = bmi;
        }

        public boolean isPositive() {
            return isPositive;
        }

        public void setPositive(boolean positive) {
            isPositive = positive;
        }
    }
    @Test
    public void should_have_default_generators_for_all_most_used_classes() throws InvocationTargetException, IllegalAccessException {
        BeanFactory beanFactory=new BeanFactory();
        ClassWithDefaults classWithDefaults=beanFactory.create(ClassWithDefaults.class);
        assertThat(classWithDefaults).isNotNull();

        assertThat(classWithDefaults.getName()).isInstanceOf(String.class);
        assertThat(classWithDefaults.getName().length()).isBetween(0,10);

        assertThat(classWithDefaults.getAge()).isInstanceOf(Integer.class);
        assertThat(classWithDefaults.getAge()).isBetween(0,10000);

        assertThat(classWithDefaults.getId()).isInstanceOf(Long.class);
        assertThat(classWithDefaults.getId()).isBetween(0L,10000L);

        assertThat(classWithDefaults.getWeight()).isInstanceOf(Float.class);
        assertThat(classWithDefaults.getWeight()).isBetween((float)0,(float)10000);

        assertThat(classWithDefaults.getBmi()).isInstanceOf(Double.class);
        assertThat(classWithDefaults.getBmi()).isBetween((double)0,(double)10000);

        assertThat(classWithDefaults.isPositive()).isInstanceOf(Boolean.class);

    }
    interface F1{
        public void hello();
    }
    public static class F1Use{
        F1 f1;

        public F1 getF1() {
            return f1;
        }

        public void setF1(F1 f1) {
            this.f1 = f1;
        }
    }
    @Test
    public void wont_support_custome_interfaces() throws IllegalAccessException, InvocationTargetException {
        BeanFactory beanFactory=new BeanFactory();
        F1Use f1Use=beanFactory.create(F1Use.class);
        assertThat(f1Use).isNotNull();
        assertThat(f1Use.getF1()).isNull();
    }

}
