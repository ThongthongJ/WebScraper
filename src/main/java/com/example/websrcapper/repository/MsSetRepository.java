package com.example.websrcapper.repository;

import com.example.websrcapper.entity.MsSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface MsSetRepository extends JpaRepository<MsSetEntity,String> {

}
