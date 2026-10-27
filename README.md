                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  # Smb — Jetpack Compose Starter

This project implements the attached Smb UI concept as a native Android app using:

- Kotlin
- Jetpack Compose + Material 3
- MVVM
- Hilt dependency injection
- Retrofit + OkHttp
- Coroutines / StateFlow
- Navigation Compose

## UI coverage

The project contains routes corresponding to the 18 screens shown in the reference:

1. Splash
2. Welcome / Onboarding
3. Login / Sign Up
4. Registration
5. Dasara Offer
6. Membership Plan
7. Profile Creation / Family Video
8. Voice Bio
9. Parents Talk
10. Profile View
11. Admin Voice Note
12. Matches
13. Privacy & Security
14. Interest Sent
15. Match Fixed
16. Invoice Payment
17. Contacts Locked
18. Admin Panel

## Important

The attached design is a visual reference. The starter intentionally uses Compose-drawn placeholders for profile photos, family videos, and branding rather than copying raster assets into the app.

Replace:
- `BASE_URL` in `NetworkModule.kt`
- API request/response models as needed
- demo profile data in `MainScreens.kt`
- payment integration in `InvoiceScreen`
- real video/audio capture and upload implementation
- authentication/token persistence

## API endpoints expected by the sample Retrofit layer

- POST `/auth/login`
- POST `/auth/register`
- GET `/matches`
- POST `/matches/{id}/interest`
- POST `/matches/{id}/fix`
- GET `/invoices/{id}`

## Open in Android Studio

Use a recent Android Studio release compatible with AGP 9.4, JDK 17, and Android SDK 37.

If Android Studio asks to update Gradle or dependencies, prefer the versions supported by your installed Android Studio.

## Suggested production package structure

`data/api` → Retrofit services  
`data/model` → DTO/domain models  
`data/repository` → repository layer  
`di` → Hilt modules  
`ui/screens` → Compose screens  
`ui/viewmodel` → MVVM state and business actions  
`ui/navigation` → navigation routes  
`ui/theme` → design system

## Branding

Primary color: #9D174D  
Deep maroon: #7F123D  
Background: #FFF9F5  
Success: #159447

The design is intentionally close to the provided reference: maroon CTAs, cream background, rounded cards, verification badges, family-focused onboarding, membership/offer cards, matches, privacy, invoice and admin workflows.
