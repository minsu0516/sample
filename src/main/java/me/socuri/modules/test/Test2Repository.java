package me.socuri.modules.test;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Test2Repository extends JpaRepository<Test2,TestPK> {
}
