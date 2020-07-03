// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    List<TimeRange> possibleTimes = new ArrayList<TimeRange>();

    if (request.getDuration() > TimeRange.WHOLE_DAY.duration()) {
      return possibleTimes;
    }

    if (request.getAttendees().isEmpty()) {
      return Arrays.asList(TimeRange.WHOLE_DAY);
    }

    int earliestPossible = TimeRange.START_OF_DAY;

    for (Event event : events) {
      if (!isEventConsidered(event, request)) {
        //ignore event that should not be considered
        continue;
      }

      if (earliestPossible < event.getWhen().start()) {
        addIfEnoughRoom(possibleTimes, TimeRange.fromStartEnd(earliestPossible, event.getWhen().start(), false), request.getDuration());
      }
      if (event.getWhen().end() > earliestPossible) {
        earliestPossible = event.getWhen().end();
      }
    }

    if (earliestPossible < TimeRange.END_OF_DAY) {
      addIfEnoughRoom(possibleTimes, TimeRange.fromStartEnd(earliestPossible, TimeRange.END_OF_DAY, true), request.getDuration());
    }

    return possibleTimes;
  }

  private boolean isEventConsidered (Event event, MeetingRequest request) {
    for (String attendee: event.getAttendees()) {
      if (request.getAttendees().contains(attendee)) {
        return true;
      }
    }
    return false;
  }

  private List<TimeRange> addIfEnoughRoom (List<TimeRange> possibleTimes, TimeRange range, long duration) {
    if (range.duration() >= duration) {
      possibleTimes.add(range);
    }
    return possibleTimes;
  }
}
