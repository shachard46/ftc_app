package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

public class Teleop extends LinearOpMode  {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDriveFront = null;
    private DcMotor leftDriveBack = null;
    private DcMotor rightDriveFront = null;
    private DcMotor rightDriveBack = null;
    private DcMotor plate = null;
    private DcMotor leftCollect = null;
    private DcMotor rightCollect = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDriveFront  = hardwareMap.get(DcMotor.class, "left_drive_front");
        leftDriveBack = hardwareMap.get(DcMotor.class, "left_drive_back");
        rightDriveFront  = hardwareMap.get(DcMotor.class, "right_drive_front");
        rightDriveBack = hardwareMap.get(DcMotor.class, "right_drive_back");
        plate = hardwareMap.get(DcMotor.class, "plate");
        leftCollect= hardwareMap.get(DcMotor.class, "left_collect");
        rightCollect= hardwareMap.get(DcMotor.class, "right_collect");


        leftDriveFront.setDirection(DcMotor.Direction.FORWARD);
        leftDriveBack.setDirection(DcMotor.Direction.FORWARD);
        rightDriveFront.setDirection(DcMotor.Direction.REVERSE);
        rightDriveBack.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        while (opModeIsActive()) {
            Drive();
            plate();
            collect();
        }
    }
    public void Drive(){
        double leftSideSpeed = gamepad1.left_trigger;
        double rightSideSpeed = gamepad1.right_trigger;
        double leftSpeed = gamepad1.left_stick_y;
        double rightSpeed = gamepad1.right_stick_y;
        if (leftSideSpeed == 0 && rightSideSpeed == 0){
            leftDriveFront.setPower(leftSpeed);
            leftDriveBack.setPower(leftSpeed);
            rightDriveFront.setPower(rightSpeed);
            rightDriveBack.setPower(rightSpeed);
        }
        else if(leftSpeed == 0 && rightSpeed == 0){
            leftDriveFront.setPower(leftSideSpeed);
            leftDriveBack.setPower(-leftSideSpeed);
            rightDriveFront.setPower(-rightSideSpeed);
            rightDriveBack.setPower(rightSideSpeed);
        }
    }
    public void plate(){
        if (gamepad2.x){
            plate.setPower(0.6);
        }
        else if(gamepad2.b){
            plate.setPower(-0.3);
        }
    }
    public void collect(){
        double collect = gamepad2.left_trigger;
        double release = gamepad2.right_trigger;
        if (release == 0){
            leftCollect.setPower(-collect);
            rightCollect.setPower(collect);
        }
        else if (collect == 0){
            leftCollect.setPower(release);
            rightCollect.setPower(release);
        }
    }
}
