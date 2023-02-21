package com.project.model.sport.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.project.model.user.BaseUser;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseSport {
    protected UUID id;
    protected String title;
    protected BaseUser user;


}
