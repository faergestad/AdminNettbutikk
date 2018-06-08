package no.usn.a151710.hobbyhusetv2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class VarerFragment extends Fragment {

    private List<Vare> vareListe;
    private RecyclerView.Adapter adapter;
    // Sti til API på local server
    public static final String JSON_URL = "http://10.0.2.2/hobbyhuset/api.php/Vare?order=LagerAntall,desc&transform=1";

    public VarerFragment() {
        // Tom konstruktør
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_varer, container, false);

        getActivity().setTitle("Varer");

        RecyclerView mRecyclerview = view.findViewById(R.id.vare_recycler);

        vareListe = new ArrayList<>();
        adapter = new VareAdapter(getContext(),vareListe);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerview.getContext(), linearLayoutManager.getOrientation());

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        mRecyclerview.addItemDecoration(dividerItemDecoration);
        mRecyclerview.setAdapter(adapter);

        getData();
        return view;
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Laster...");
        progressDialog.show();

        StringRequest jsonStringRequest = new StringRequest(JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray jsonVareArray = jsonObject.optJSONArray("Vare");

                for (int i = 0; i < jsonVareArray.length(); i++) {
                    try {
                        JSONObject jsonVare = (JSONObject)jsonVareArray.get(i);
                        Vare vare = new Vare(jsonVare);

                        vare.setVarekode(parseInt(jsonVare.getString("Varekode")));
                        vare.setBetegnelse(jsonVare.getString("Betegnelse"));
                        vare.setPrisPrEnhet(parseDouble(jsonVare.getString("PrisPrEnhet")));
                        vare.setLagerAntall(parseInt(jsonVare.getString("LagerAntall")));
                        vare.setAktiv(parseInt(jsonVare.getString("Aktiv")));


                        vareListe.add(vare);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressDialog.dismiss();
                    }
                }
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonStringRequest);
    }

}