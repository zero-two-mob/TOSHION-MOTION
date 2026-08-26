# TOSHION MOTION

A native Android video editor, built in phases toward Alight Motion–level
workflow: layer-based timeline, GPU-accelerated preview, keyframe animation,
a modular effects engine, and hardware-accelerated multi-codec export.

(Internal package name stays `com.toshion.motion` — renaming that
touches all 61 files' package declarations for no functional gain; the
user-facing name is TOSHION MOTION everywhere it's shown.)

## Status: Phase 4 (Timeline System) built

**Database migration this pass — read before installing over an existing
copy.** Adding the clips table required bumping the Room schema version.
It's a real migration (`MIGRATION_1_2`, additive only), not a destructive
reset, so any project already saved on-device should survive the update.
Manually verified the migration's SQL matches the new entity column-by-
column, but this is exactly the kind of thing worth a quick check after
installing: open the app, confirm your existing project is still there.

**Built for real:**
- `Clip` domain model + Room table + repository + 5 use cases (Get/Add/
  Trim/Split/Delete), Clean-Architecture-consistent with everything else
- Multi-clip ExoPlayer playlist — each clip becomes a `MediaItem` with its
  own `ClippingConfiguration`, so per-clip in/out trimming actually plays
  back correctly, not just visually
- Global timeline position tracking across clip boundaries (a playlist
  player's own position resets per-item; this reconstructs a real
  timeline-wide position/seek from it)
- Timeline UI: ruler, zoom (+/- buttons, not pinch — see below), horizontal
  scroll, tap a clip to select it, tap empty timeline to seek, drag handles
  to trim the selected clip's in/out points (commits once on release, not
  per-frame, so it doesn't hammer the database mid-drag)
- Add Clip (Photo Picker, appends to the end), Split at playhead, Ripple
  delete (removes + closes the gap)
- Gapless single-track model: clips always sit back-to-back with no manual
  free-positioning. This is a deliberate simplification — it's what gives
  "nothing can create a gap" for free (the spec's Magnetic Timeline) and
  makes ripple delete simple re-packing instead of collision math
- Creating a project now drops you straight into the Editor with your
  picked media already seeded as the first clip

**Deliberately not built, and why:** multi-select, drag-to-reorder clips,
groups/nested groups, layer collapse/expand, layer colors, timeline
markers — most of these need actual multi-track layer types to be
meaningful, which is Phase 5's Layer Engine, not Phase 4's job. Pinch-to-
zoom was considered and dropped in favor of +/- buttons — combining custom
pinch detection with a scrollable container is real gesture-conflict risk
I can't test my way out of; zoom buttons get the same functional result
with no such risk.

## Building

Same as always: **Add file → Upload files** with this zip (replaces the
old one), Actions tab builds it, grab the APK from the finished run's
artifacts.

## Next up

Phase 5: the Layer Engine — video/image/audio/text/shape/adjustment/mask
layer types, multi-track support, opacity/blend modes/transforms. This is
what turns "one track of clips" into the real multi-layer composition a
video editor needs.
