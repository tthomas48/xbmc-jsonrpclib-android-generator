/*
 *      Copyright (C) 2005-2012 Team XBMC
 *      http://xbmc.org
 *
 *  This Program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2, or (at your option)
 *  any later version.
 *
 *  This Program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with XBMC Remote; see the file license.  If not, write to
 *  the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.
 *  http://www.gnu.org/copyleft/gpl.html
 *
 */
package org.xbmc.android.jsonrpc.generator.introspect.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.xbmc.android.jsonrpc.generator.Introspect;
import org.xbmc.android.jsonrpc.generator.introspect.wrapper.ExtendsWrapper;

/**
 * Deserializes the <tt>extends</tt> value into {@link ExtendsWrapper}.
 * <p/>
 * 
 * The reason for that is that <tt>extends</tt> can be multiple types, which 
 * doesn't work well (like, not at all) with Java.
 *  
 * @author freezy <freezy@xbmc.org>
 */
public class ExtendsDeserializer extends Deserializer<ExtendsWrapper> {
	
	@Override
	public ExtendsWrapper deserialize(JsonParser jsonParser, DeserializationContext c) throws IOException, JsonProcessingException {
		final ObjectCodec oc = jsonParser.getCodec();
		final JsonNode node = oc.readTree(jsonParser);
		if (node.isArray()) {
			return new ExtendsWrapper(new ArrayList<String>(Arrays.asList(Introspect.OBJECT_MAPPER.readValue(node, String[].class))));
		} else {
			return new ExtendsWrapper(node.getTextValue());
		}
	}
}
