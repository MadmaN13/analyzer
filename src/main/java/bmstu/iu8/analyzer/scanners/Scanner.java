package main.java.bmstu.iu8.analyzer.scanners;

import java.util.Collection;

import main.java.bmstu.iu8.analyzer.model.Alert;
import main.java.bmstu.iu8.analyzer.model.Weakness;

public interface Scanner {

	public Collection<Alert> scan(Collection<Weakness> weaknesses);
}
