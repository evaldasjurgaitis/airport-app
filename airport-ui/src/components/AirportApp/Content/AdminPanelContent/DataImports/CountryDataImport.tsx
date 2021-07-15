import React, { useState } from 'react';
import axios from 'axios';

import { Button, Upload } from 'antd';
import { UploadOutlined } from '@ant-design/icons';

import Loader from '../../../../shared/Loader/Loader';
import Row from '../../../../shared/Row/Row';
import Col from '../../../../shared/Col/Col';

import { JobStatus } from '../../../typing';

const CountryDataImport = () => {
    const [showCountryDataLoader, setShowCountryDataLoader] = useState(false);
    const [showCountryDataImportBtn, setShowCountryDataImportBtn] = useState(false);
    const [countryDataFilePath, setCountryDataFilePath] = useState('');
    const [showCountryDataImportStatusFailure, setShowCountryDataImportStatusFailure] = useState(false);
    const [showCountryDataImportStatusSuccess, setShowCountryDataImportStatusSuccess] = useState(false);

    const fileForm = (options: any) => {
        const { file } = options;
        const data = new FormData();
        data.append('file', file);
        return data;
    };

    const uploadCountryData = async (options: any) => {
        setShowCountryDataLoader(true);
        setShowCountryDataImportBtn(false);
        setShowCountryDataImportStatusFailure(false);
        setShowCountryDataImportStatusSuccess(false);
        await axios
            .post(`http://localhost:8082/api/files`, fileForm(options))
            .then((response) => {
                setShowCountryDataLoader(false);
                setShowCountryDataImportBtn(true);
                setCountryDataFilePath(response.data.path);
            })
            .catch(() => {
                setShowCountryDataLoader(false);
                setShowCountryDataImportStatusFailure(true);
            });
    };

    const pollingImportStatus = (jobId: number) => {
        axios
            .get(`http://localhost:8082/api/jobs/${jobId}/status`)
            .then((res) => {
                if (
                    (res.status === 200 && res.data.status === JobStatus.STARTED) ||
                    res.data.status === JobStatus.STARTING
                ) {
                    setTimeout(() => pollingImportStatus(jobId), 5000);
                } else {
                    if (res.data.status === JobStatus.COMPLETED) {
                        setShowCountryDataLoader(false);
                        setShowCountryDataImportStatusSuccess(true);
                    }
                    if (
                        !res ||
                        res.data.status === JobStatus.FAILED ||
                        res.data.status === JobStatus.STOPPING ||
                        res.data.status === JobStatus.STOPPED ||
                        res.data.status === JobStatus.ABANDONED
                    ) {
                        setShowCountryDataLoader(false);
                        setShowCountryDataImportStatusFailure(true);
                    }
                }
            })
            .catch(function () {
                setShowCountryDataLoader(false);
                setShowCountryDataImportStatusFailure(true);
            });
    };

    const executeImportData = async () => {
        setShowCountryDataImportBtn(false);
        setShowCountryDataLoader(true);
        axios
            .post('http://localhost:8082/api/jobs/COUNTRY_DATA_IMPORT/execute', {
                filePath: countryDataFilePath,
            })
            .then(async () => {
                setTimeout(() => {
                    axios
                        .get('http://localhost:8082/api/jobs/COUNTRY_DATA_IMPORT')
                        .then(async (response) => {
                            pollingImportStatus(response.data.id);
                        })
                        .catch(() => {
                            setShowCountryDataLoader(false);
                            setShowCountryDataImportStatusFailure(true);
                        });
                }, 4500);
            })
            .catch(() => {
                setShowCountryDataLoader(false);
                setShowCountryDataImportStatusFailure(true);
            });
    };

    return (
        <Row align="middle" className="admin-panel--row">
            <Col span={3} xs={10} sm={3} md={5} xl={3}>
                <div className="align-left">
                    <Upload accept=".csv" customRequest={uploadCountryData} showUploadList={false} maxCount={1}>
                        <Button disabled={showCountryDataLoader} icon={<UploadOutlined />}>
                            Countries upload
                        </Button>
                    </Upload>
                </div>
            </Col>
            <Col span={3} xs={9} sm={3} md={4} xl={2}>
                <div className="align-center">
                    {showCountryDataLoader && <Loader />}
                    {showCountryDataImportBtn && <Button onClick={executeImportData}>Import data</Button>}
                    {showCountryDataImportStatusSuccess && (
                        <span className="admin-panel--import-data__success">Success</span>
                    )}
                    {showCountryDataImportStatusFailure && (
                        <span className="admin-panel--import-data__failure">Failure</span>
                    )}
                </div>
            </Col>
        </Row>
    );
};

export default CountryDataImport;
