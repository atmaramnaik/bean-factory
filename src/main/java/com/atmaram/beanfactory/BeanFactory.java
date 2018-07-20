package com.atmaram.beanfactory;

import com.atmaram.beanfactory.generators.*;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class BeanFactory {
    ArrayList<GeneratorBuilder> generatorBuilders=new ArrayList<>();
    public GeneratorBuilder apply(Generator generator){
        GeneratorBuilder generatorBuilder=new GeneratorBuilder(generator);
        generatorBuilders.add(generatorBuilder);
        return generatorBuilder;
    }
    private <T> Generator getDefaultGeneratorBasedOnClass(Class<T> tClass) {
        if (tClass.equals(String.class)){
            return new StringGenerator();
        } else if(tClass.equals(int.class) || tClass.equals(Integer.class)){
            return new IntegerGenerator();
        } else if(tClass.equals(boolean.class) || tClass.equals(Boolean.class)){
            return new BooleanGenerator();
        } else if(tClass.equals(long.class) || tClass.equals(Long.class)){
            return new LongGenerator();
        }else if(tClass.equals(float.class) || tClass.equals(Float.class)){
            return new FloatGenerator();
        }else if(tClass.equals(double.class) || tClass.equals(Double.class)){
            return new DoubleGenerator();
        }
        return null;
    }
    public <T> T create(Class<T> tClass) throws IllegalAccessException,InvocationTargetException {
        Generator generator= getDefaultGeneratorBasedOnClass(tClass);
        if(generator!=null){
            return (T)generator.generate();
        }
        try {
            T obj=tClass.newInstance();


            List<Method> methods=Arrays.asList(tClass.getMethods()).stream().filter(method -> {return !generatorBuilders.stream().map(generatorBuilder -> {return generatorBuilder.getMethod();}).collect(Collectors.toList()).contains(method) && method.getName().startsWith("set");}).collect(Collectors.toList());
            for (Method method:
                 methods) {

                Object[] args=new Object[1];
                Type type=method.getGenericParameterTypes()[0];
                if(type instanceof ParameterizedType) {
                    args[0]=getGeneric(type);
                }else{
                    args[0]=this.create((Class) type);
                }
                method.invoke(obj,args);


            }
            generatorBuilders.stream().filter(generatorBuilder -> (generatorBuilder.gettClass().equals(tClass))).map(generatorBuilder -> {
                Object[] args=new Object[1];
                if(generatorBuilder.getGenerator() instanceof ListGenerator){
                    setMemeberGeneratorForList(generatorBuilder);
                } else if(generatorBuilder.getGenerator() instanceof MapGenerator){
                    setMemeberGeneratorForMap(generatorBuilder);
                }
                args[0]=generatorBuilder.getGenerator().generate();
                try {
                    generatorBuilder.getMethod().invoke(obj, args);
                } catch (IllegalAccessException e) {
                    return false;
                } catch (InvocationTargetException e) {
                    return false;
                }
                return true;
            }).reduce(true,(x,y)->x && y);
            return obj;
        } catch (InstantiationException ex){
            return null;
        }
    }
    private void setMemeberGeneratorForList(GeneratorBuilder generatorBuilder){
        ListGenerator listGenerator=(ListGenerator)generatorBuilder.getGenerator();
        if(listGenerator.getMemberGenerator() == null) {
            ParameterizedType parameterizedType = (ParameterizedType) generatorBuilder.getMethod().getGenericParameterTypes()[0];
            Class memberClass =
                    ((Class) parameterizedType.getActualTypeArguments()[0]);
            Generator generator1 = null;
            try {
                generator1 = getDefaultGeneratorBasedOnClass(memberClass);
                if (generator1 == null) {
                    generator1 = new Generator() {
                        @Override
                        public Object generate() {
                            try {
                                return BeanFactory.this.create(memberClass);
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();

                            }
                            return null;
                        }
                    };
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            listGenerator.setMemberGenerator(generator1);
        }
    }
    private void setMemeberGeneratorForMap(GeneratorBuilder generatorBuilder){
        MapGenerator mapGenerator=(MapGenerator) generatorBuilder.getGenerator();
        if(mapGenerator.getKeyGenerator() == null) {
            ParameterizedType parameterizedType = (ParameterizedType) generatorBuilder.getMethod().getGenericParameterTypes()[0];
            Class memberClass =
                    ((Class) parameterizedType.getActualTypeArguments()[0]);
            Generator generator1 = null;
            try {
                generator1 = getDefaultGeneratorBasedOnClass(memberClass);
                if (generator1 == null) {
                    generator1 = new Generator() {
                        @Override
                        public Object generate() {
                            try {
                                return BeanFactory.this.create(memberClass);
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();

                            }
                            return null;
                        }
                    };
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            mapGenerator.setKeyGenerator(generator1);
        }
        if(mapGenerator.getValueGenerator() == null) {
            ParameterizedType parameterizedType = (ParameterizedType) generatorBuilder.getMethod().getGenericParameterTypes()[0];
            Class memberClass =
                    ((Class) parameterizedType.getActualTypeArguments()[1]);
            Generator generator1 = null;
            try {
                generator1 = getDefaultGeneratorBasedOnClass(memberClass);
                if (generator1 == null) {
                    generator1 = new Generator() {
                        @Override
                        public Object generate() {
                            try {
                                return BeanFactory.this.create(memberClass);
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();

                            }
                            return null;
                        }
                    };
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            mapGenerator.setValueGenerator(generator1);
        }
    }
    private Object getGeneric(Type type) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        if(List.class.isAssignableFrom(((ParameterizedTypeImpl)type).getRawType())) {
            Type memberType=((ParameterizedType) type).getActualTypeArguments()[0];
            if(memberType instanceof ParameterizedType) {
                return new ListGenerator(new Generator() {
                    @Override
                    public Object generate() {
                        try {
                            return getGeneric(memberType);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                            return null;
                        }
                    }
                }).generate();
            } else {
                ListGenerator generator1 = new ListGenerator(getDefaultGeneratorBasedOnClass((Class) ((ParameterizedType) type).getActualTypeArguments()[0]));
                if (generator1.getMemberGenerator() == null) {
                    generator1.setMemberGenerator(new Generator() {
                        @Override
                        public Object generate() {
                            try {
                                return BeanFactory.this.create((Class) ((ParameterizedType) type).getActualTypeArguments()[0]);
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                                return null;
                            }
                        }
                    });

                }
                return generator1.generate();
            }
        } else if(Map.class.isAssignableFrom(((ParameterizedTypeImpl)type).getRawType())) {
            Type keyType=((ParameterizedType) type).getActualTypeArguments()[0];
            Type valueType=((ParameterizedType) type).getActualTypeArguments()[1];
            MapGenerator mapGenerator=new MapGenerator();
            if(keyType instanceof ParameterizedType) {
                mapGenerator.setKeyGenerator(new Generator() {
                    @Override
                    public Object generate() {
                        try {
                            return getGeneric(keyType);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                            return null;
                        }
                    }
                });
            } else {
                Generator keyGenerator= getDefaultGeneratorBasedOnClass((Class) ((ParameterizedType) type).getActualTypeArguments()[0]);
                if (keyGenerator == null) {
                    keyGenerator=new Generator() {
                        @Override
                        public Object generate() {
                            try {
                                return BeanFactory.this.create((Class) ((ParameterizedType) type).getActualTypeArguments()[0]);
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                                return null;
                            }
                        }
                    };

                }
                mapGenerator.setKeyGenerator(keyGenerator);
            }
            if(valueType instanceof ParameterizedType) {
                mapGenerator.setKeyGenerator(new Generator() {
                    @Override
                    public Object generate() {
                        try {
                            return getGeneric(valueType);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                            return null;
                        }
                    }
                });
            } else {
                Generator valueGenerator= getDefaultGeneratorBasedOnClass((Class) ((ParameterizedType) type).getActualTypeArguments()[1]);
                if (valueGenerator == null) {
                    valueGenerator=new Generator() {
                        @Override
                        public Object generate() {
                            try {
                                return BeanFactory.this.create((Class) ((ParameterizedType) type).getActualTypeArguments()[1]);
                            } catch (Throwable throwable) {
                                throwable.printStackTrace();
                                return null;
                            }
                        }
                    };

                }
                mapGenerator.setValueGenerator(valueGenerator);
            }
            return mapGenerator.generate();
        } else{
            return this.create(((ParameterizedTypeImpl) type).getRawType());
        }
    }
}
