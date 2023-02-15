package model.sport.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Publication;
import model.Role;
import model.user.BaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseSport {
    protected UUID id;
    protected String title;
    protected BaseUser user;


}
