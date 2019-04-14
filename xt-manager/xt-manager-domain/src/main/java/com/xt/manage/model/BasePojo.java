package com.xt.manage.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public abstract class BasePojo  implements Serializable {

	private static final long serialVersionUID = -1764615629102428742L;

	private Date created;

    private Date updated;

}
