package au.com.emberspring.ember;

import com.google.common.base.CaseFormat;
import org.atteo.evo.inflector.English;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


@SuppressWarnings("serial")
public final class EmberModel extends ConcurrentHashMap<String, Object> {

    private EmberModel() {
        //Must use the builder
    }

    public static class Builder<T> implements au.com.emberspring.patterns.Builder<EmberModel> {
        private final ConcurrentMap<String, Object> sideLoadedItems = new ConcurrentHashMap<String, Object>();
        private final ConcurrentMap<String, Object> metaData = new ConcurrentHashMap<String, Object>();

        public Builder(final Object entity) {
            Assert.notNull(entity);
            sideLoad(entity);
        }

        public Builder(final Class<T> clazz, final Collection<T> entities) {
            Assert.notNull(entities);
            sideLoad(clazz, entities);
        }

        public Builder(final String rootName, final Collection<?> entities) {
            Assert.notNull(entities);
            sideLoad(rootName, entities);
        }

        public Builder<T> addMeta(final String key, final Object value) {
            metaData.put(key, value);
            return this;
        }

        public Builder<T> sideLoad(final Object entity) {
            if (entity != null) {
                sideLoadedItems.put(getSingularName(entity.getClass()), entity);
            }
            return this;
        }

        public <K> Builder<T> sideLoad(final Class<K> clazz, final Collection<K> entities) {
            if (entities != null) {
                sideLoadedItems.put(getPluralName(clazz), entities);
            }
            return this;
        }

        public Builder<T> sideLoad(final String rootName, final Collection<?> entities) {
            if (entities != null) {
                sideLoadedItems.put(English.plural(rootName), entities);
            }
            return this;
        }

        private String getSingularName(final Class<?> clazz) {
            Assert.notNull(clazz);
            return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, clazz.getSimpleName());
        }

        private String getPluralName(final Class<?> clazz) {
            return English.plural(getSingularName(clazz));
        }

        @Override
        public EmberModel build() {
            if (metaData.size() > 0) {
                sideLoadedItems.put("meta", metaData);
            }
            EmberModel sideLoader = new EmberModel();
            sideLoader.putAll(sideLoadedItems);
            return sideLoader;
        }
    }
}
