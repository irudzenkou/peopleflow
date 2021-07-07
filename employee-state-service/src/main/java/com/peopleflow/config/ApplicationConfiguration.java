package com.peopleflow.config;

import com.peopleflow.core.EmployeeState;
import com.peopleflow.dao.EmployeeRepository;
import com.peopleflow.service.EmployeeListener;
import com.peopleflow.service.EmployeeService;
import com.peopleflow.service.impl.EmployeeServiceImpl;
import com.peopleflow.service.state.EmployeeEvent;
import com.peopleflow.service.state.EmployeeStateMachineProcessor;
import com.peopleflow.service.state.EmployeeStateProcessor;
import com.peopleflow.service.state.persist.EmployeeStateMachineStorage;
import com.peopleflow.service.state.persist.InMemoryStateMachinePersister;
import com.peopleflow.service.state.persist.StateMachineStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public EmployeeStateProcessor stateProcessor(StateMachineStorage stateMachineStorage) {
        return new EmployeeStateMachineProcessor(stateMachineStorage);
    }

    @Bean
    public EmployeeService employeeService(EmployeeRepository employeeRepository/*, EmployeeMapper employeeMapper*/) {
        return new EmployeeServiceImpl(employeeRepository/*, employeeMapper*/);
    }

    @Bean
    public EmployeeListener employeeEventListener(EmployeeStateProcessor stateProcessor) {
        return new EmployeeListener(stateProcessor);
    }

    @Bean
    public RecordMessageConverter messageConverter() {
        return new StringJsonMessageConverter();
    }

//    @Bean
//    public StateMachineUtils stateMachineUtils() {
//        return new StateMachineUtils();
//    }

//    @Bean
//    public ActionFactory actionFactory(EmployeeService employeeService, StateMachineUtils stateMachineUtils) {
//        return new ActionFactory(employeeService, stateMachineUtils);
//    }

    @Bean
    public StateMachineStorage stateMachineStorage(StateMachinePersister<EmployeeState, EmployeeEvent, String> persister,
                                                   StateMachineFactory<EmployeeState, EmployeeEvent> stateMachineFactory) {
        return new EmployeeStateMachineStorage(persister, stateMachineFactory);
    }

    @Bean
    public StateMachinePersister<EmployeeState, EmployeeEvent, String> persister() {
        return new DefaultStateMachinePersister<>(new InMemoryStateMachinePersister());
    }
}
