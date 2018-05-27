package ms.domain.modules.config.type;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


public class RoutingDataSource extends AbstractRoutingDataSource {
  @Override
  protected Object determineCurrentLookupKey() {
    return ContextHolder.getDataSourceType();
  }
}