// Fill out your copyright notice in the Description page of Project Settings.


#include "testCharacter.h"

#include "DissProject4_24.h"

// Sets default values
AtestCharacter::AtestCharacter()
{
 	// Set this character to call Tick() every frame.  You can turn this off to improve performance if you don't need it.
	PrimaryActorTick.bCanEverTick = true;

	VROriginComp = CreateDefaultSubobject<USceneComponent>(TEXT("VRCameraOrigin"));
	VROriginComp->SetupAttachment(RootComponent);

	CameraComp = CreateDefaultSubobject<UCameraComponent>(TEXT("CameraComponent"));
	/* Assign to the VR origin component so any reset calls to the HMD can reset to 0,0,0 relative to this component */
	CameraComp->SetupAttachment(VROriginComp);


	MC_right = CreateDefaultSubobject<UMotionControllerComponent>(TEXT("RightHand"));
	MC_right->MotionSource = "RightWristCenter";
	MC_right->SetupAttachment(VROriginComp);

	SM_right = CreateDefaultSubobject<UStaticMeshComponent>(TEXT("RightHandMesh"));
	SM_right->SetupAttachment(MC_right);

}

// Called when the game starts or when spawned
void AtestCharacter::BeginPlay()
{
	Super::BeginPlay();

	FVector loc = SM_right->GetComponentLocation();
	
	
}

// Called every frame
void AtestCharacter::Tick(float DeltaTime)
{
	Super::Tick(DeltaTime);

}

// Called to bind functionality to input
void AtestCharacter::SetupPlayerInputComponent(UInputComponent* PlayerInputComponent)
{
	Super::SetupPlayerInputComponent(PlayerInputComponent);

}

