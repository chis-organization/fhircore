/*
 * Copyright 2021-2024 Ona Systems, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.smartregister.fhircore.engine.configuration.view

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import org.smartregister.fhircore.engine.domain.model.ViewType
import org.smartregister.fhircore.engine.util.extension.interpolate

@Serializable
@Parcelize
data class CardViewProperties(
  override val viewType: ViewType,
  override val weight: Float = 0f,
  override val backgroundColor: String? = null,
  override val padding: Int = 0,
  override val borderRadius: Int = 2,
  override val alignment: ViewAlignment = ViewAlignment.NONE,
  override val fillMaxWidth: Boolean = false,
  override val fillMaxHeight: Boolean = false,
  override val clickable: String = "true",
  override val visible: String = "true",
  override val opacity: Float? = null,
  val content: List<ViewProperties> = emptyList(),
  val elevation: Int = 5,
  val cornerSize: Int = 6,
  val header: CompoundTextProperties? = null,
  val headerBackgroundColor: String = "#F2F4F7",
  val headerAction: CompoundTextProperties? = null,
  val emptyContentMessage: String = "",
  val contentPadding: Int = 16,
) : ViewProperties(), Parcelable {
  override fun interpolate(computedValuesMap: Map<String, Any>): CardViewProperties {
    return this.copy(
      backgroundColor = backgroundColor?.interpolate(computedValuesMap),
      visible = visible.interpolate(computedValuesMap),
      headerAction = headerAction?.interpolate(computedValuesMap),
      header = header?.interpolate(computedValuesMap),
    )
  }
}
