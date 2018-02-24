package com.team687.frc2018;

import java.io.FileWriter;
import java.io.IOException;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.modifiers.TankModifier;

public class Main {

    public static void main(String[] args) throws IOException {
	Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC,
		Trajectory.Config.SAMPLES_HIGH, 0.05, 60, 60, 100);
	Trajectory trajectory = Pathfinder.generate(AutoConstants.kRedCenterToScalePath, config);
	TankModifier drive = new TankModifier(trajectory).modify(DriveConstants.kDrivetrainWidth);

	FileWriter fw = new FileWriter("generated_paths/RedCenterToScalePath.csv", false);
	fw.write("time, right_pos, right_vel, right_accel, left_pos, left_vel, left_accel");
	for (int i = 0; i < drive.getLeftTrajectory().length(); i++) {
	    fw.write((i * 0.05) + "," + drive.getLeftTrajectory().get(i).position + ","
		    + drive.getLeftTrajectory().get(i).velocity + "," + drive.getLeftTrajectory().get(i).acceleration
		    + "," + drive.getRightTrajectory().get(i).position + ","
		    + drive.getRightTrajectory().get(i).velocity + "," + drive.getRightTrajectory().get(i).acceleration
		    + ",");
	    fw.write("\n");
	}
	fw.flush();
	fw.close();
    }

}
