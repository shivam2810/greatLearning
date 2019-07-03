package com.operative.demo.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Player {
	String pId;
	String pEmail;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Player other = (Player) obj;
		if (pId == null) {
			if (other.pId != null) {
				return false;
			}
		} else if (!pId.equals(other.pId)) {
			return false;
		}
		return true;
	}

}
